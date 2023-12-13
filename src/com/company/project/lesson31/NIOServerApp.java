package com.company.project.lesson31;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NIOServerApp {
    private InetSocketAddress address;

    public NIOServerApp(InetSocketAddress address) {
        this.address = address;
    }

    public void start() {
        new NioThread().start();
    }

    private class NioThread extends Thread {
        private ServerSocketChannel serverChannel;
        private HashSet<SocketChannel> channels = new HashSet<>();
        private static final int BUF_SIZE = 1024;
        private ByteBuffer byteBuffer;
        private Selector selector;

        private void init() throws IOException {
            // инициализация буфера:
            // 1. ЧТЕНИЕ: в буфер будут приходить байты
            // из сокет соединения (канал -> буфер -> программа);
            // 2. ЗАПИСЬ: из буфера будут уходить байты
            // в сокет соединение (программа -> буфер -> канал).
            byteBuffer = ByteBuffer.allocate(BUF_SIZE);

            // инициализация селектора:
            // 1. когда в канале подключений произойдет событие,
            // информация об этом сохранится в коллекцию селектора
            // 2. когда в канале записи / чтения произойдет событие,
            // информация об этом сохранится в коллекцию селектора - Set<SelectionKey>
            // Экземпляр SelectionKey хранит информацию о событии.
            // Это может быть событие:
            // OP_ACCEPT - новое подключение, метод selectionKey.isAcceptable() вернет true
            // OP_READ - появились данные для чтения, метод selectionKey.isReadable() вернет true
            // OP_WRITE - канал готов к отправке данных, метод selectionKey.isWritable() вернет true
            selector = Selector.open();

            // инициализация ServerSocketChannel (аналог ServerSocket)
            serverChannel = ServerSocketChannel.open();

            // ServerSocketChannel связывается с определенным портом
            serverChannel.bind(address);

            // перевод в неблокирующий режим (иначе в канале не смогут происходить разные события)
            serverChannel.configureBlocking(false);

            // регистрация канала в селекторе:
            // селектор будет сохранять в коллекции информацию при каждом новом подключении,
            // информация сохранится как экземпляр SelectionKey(OP_ACCEPT)
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // SelectionKey.WRITE | SelectionKey.READ
        }

        private void chooseEventReaction() throws IOException {
            // ожидание новых событий, новой информации в коллекции селектора
            // программа приостанавливает работу, пока не произойдет новое событие
            selector.select();

            // получение коллекции селектора с информацией о событиях
            Set<SelectionKey> keys = selector.selectedKeys(); // можно удалять объекты
            // Set<SelectionKey> keys = selector.keys(); // нельзя удалять объекты


            // перебор итератором позволит удалить информацию о событии из коллекции
            // после того, как она будет обработана
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                // selectionKey содержит информацию о событии
                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isAcceptable()) { // true, если событие OP_ACCEPT - новое подключение
                    // получение канала (аналог Socket), ассоциирован с соединением клиент - сервер
                    SocketChannel channel = serverChannel.accept();
                    channels.add(channel);

                    // перевод в неблокирующий режим (иначе в канале не смогут происходить разные события)
                    channel.configureBlocking(false);

                    // регистрация канала в селекторе:
                    // селектор будет сохранять в коллекции информацию, когда в канале будут появляться данные для чтения,
                    // информация сохранится как экземпляр SelectionKey (OP_READ)
                    channel.register(selector, SelectionKey.OP_READ);
                    System.out.println("Client connected " + channel.getRemoteAddress());
                } else if (selectionKey.isReadable()) { // true, если событие OP_READ - в канале есть данные для чтения
                    System.out.println("Client has sent data");
                    // если в канале есть данные для чтения,
                    // их нужно прочитать и разослать всем клиентам
                    try {
                        dataDistribution((SocketChannel) selectionKey.channel());
                    } catch (IOException e) {
                        System.out.println("Client has disconnected");
                        System.out.println(channels.remove(selectionKey.channel()));
                        selectionKey.channel().close();
                    }
                }

                // объект, хранящий информацию о событии нужно удалить,
                // чтобы не обрабатывать одно событие несколько раз
                keyIterator.remove();
            }
        }

        private void dataDistribution(SocketChannel channel) throws IOException {
            // буфер должен быть готов к чтению (канал прочитает данные, которые в нем появились в буфер)
            // position (курсор) должен быть в начале буфера
            // limit (сколько данных можно поместить в буфер) должен быть равен емкости буфера,
            // чтобы использовать буфер целиком
            byteBuffer.clear();

            int read = channel.read(byteBuffer);

            if (read == -1) return;

            String fromChannel = new String(byteBuffer.array(), 0, byteBuffer.position(), StandardCharsets.UTF_8);
            System.out.println(fromChannel);

            // byteBuffer.put()

            // буфер должен быть готов к записи (данные из буфера будут записаны в канал)
            // limit (сколько данных можно забрать из буфера) должен быть равен position,
            // чтобы забрать только валидные, а не пустые байты
            // position (курсор) должен быть в начале буфера
            byteBuffer.flip();

            for (SocketChannel socketChannel : channels) {
                if (!socketChannel.equals(channel) && (socketChannel.validOps() & SelectionKey.OP_WRITE) > 0) { // если канал доступен для записи
                    socketChannel.write(byteBuffer);

                    // буфер должен быть готов к следующей записи (данные из буфера будут записаны в следующий канал)
                    // limit (сколько данных можно забрать из буфера) остается на прежнем месте,
                    // так как количество байт не изменилось
                    // position (курсор) должен быть в начале буфера
                    byteBuffer.rewind();
                }
            }
        }

        @Override
        public void run() {
            try {
                init();
            } catch (IOException e) {
                System.out.println("ошибка инициализации");
            }
            while (true) {
                try {
                    chooseEventReaction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void main(String[] args) {
        new NIOServerApp(new InetSocketAddress(2222)).start();
    }
}

