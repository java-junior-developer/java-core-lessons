package com.company.project.multithreading.lesson29;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedClientService extends Thread {
    private final List<String> tokens;
    // аналог synchronized
    private ReentrantLock reentrantLock;
    // разрешения на доступ к ресурсу
    private Semaphore semaphore;


    public SynchronizedClientService(List<String> tokens,
                                     ReentrantLock reentrantLock,
                                     Semaphore semaphore) {
        this.tokens = tokens;
        this.reentrantLock = reentrantLock;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
//        Вариант №1 потоки будут выполнять запросы последовательно
//        synchronized (tokens){
//            String token = tokens.removeLast();
//            String newToken = makeRequest(token);
//            tokens.add(newToken);
//        }
//        Вариант №2 потоки, которым не хватит токенов упадут с ошибкой
//        String token;
//        synchronized (tokens){
//            token = tokens.removeLast();
//        }
//        String newToken = makeRequest(token);
//        synchronized (tokens) {
//            tokens.add(newToken);
//        }
        String token;
        // reentrantLock.isLocked() ресурс уже заблокирован
        // reentrantLock.getQueueLength() очередь за ресурсом
        // boolean isLocked = reentrantLock.tryLock(100, TimeUnit.MILLISECONDS);
        // isLocked - true, удалось получить блокировку
        // isLocked - false, ресурс не был освобожден за 100 MILLISECONDS
        try {
            semaphore.acquire();
            reentrantLock.lock();
            token = tokens.removeLast();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
        String newToken = makeRequest(token);
        synchronized (tokens){
            tokens.add(newToken);
        }
        semaphore.release();
    }

    private String makeRequest(String token) {
        try {
            System.out.println(Thread.currentThread().getName() +
                    " connected with token " + token);
            Thread.sleep(10_000);
            return UUID.randomUUID().toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
