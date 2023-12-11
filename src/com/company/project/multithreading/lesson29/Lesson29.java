package com.company.project.multithreading.lesson29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson29 {
    public static void main(String[] args) {
        /*Fruit fruit01 = new Fruit(Fruit.FruitType.BANANA, 150, 10);
        Fruit fruit02 = new Fruit(Fruit.FruitType.BANANA, 150, 10);
        // Взаимная блокировка потоков
        Thread thread01 = new Thread(()->{
            synchronized (fruit02){
                // инструкции
                synchronized (fruit01) {
                    // инструкции
                }
            }
        });
        Thread thread02 = new Thread(()->{
            synchronized (fruit01){
                // инструкции
                synchronized (fruit02) {
                    // инструкции
                }
            }
        });
        thread01.start();
        thread02.start();*/

        List<String> tokens = new ArrayList<>(
                Arrays.asList("123", "456", "789"));

        ReentrantLock lock = new ReentrantLock(/*true*/);

        Semaphore semaphore = new Semaphore(tokens.size()/*, true*/); // 3

        // токены нужны только для подключения
        // токен можно использовать только один раз
        // использованный токен нужно заменить новым

        new SynchronizedClientService(tokens, lock, semaphore).start();
        new SynchronizedClientService(tokens, lock, semaphore).start();
        new SynchronizedClientService(tokens, lock, semaphore).start();
        new SynchronizedClientService(tokens, lock, semaphore).start();
        new SynchronizedClientService(tokens, lock, semaphore).start();


        ArrayBlockingQueue<String> tokensQueue =
                new ArrayBlockingQueue<>(3, true, Arrays.asList("123", "456", "789"));

        QueueClientService service01 = new QueueClientService(tokensQueue);

        new Thread(service01).start();
        new Thread(service01).start();
        new Thread(service01).start();
        new Thread(service01).start();

        /*
        DelayQueue<Task> tasks = new DelayQueue<>();
        tasks.put(new Task(()->{
            System.out.println("old task");
        }, LocalDateTime.now().minusDays(1)));

        tasks.put(new Task(()->{
            System.out.println("future task");
        }, LocalDateTime.now().plusMinutes(3)));

        tasks.put(new Task(()->{
            System.out.println("now task");
        }, LocalDateTime.now().plusSeconds(20)));
        */

        /*
        while (true) {
            try {
                Runnable runnable = tasks.take().getAction();
                new Thread(runnable).start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        */

        // пулы потоков
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(() -> {
            System.out.println("Задача №1");
        });
        service.execute(service01);
        service.execute(service01);
        service.execute(service01);
        service.shutdown();
        // List<Runnable> runnables = service.shutdownNow();

        Lesson29Pool pool = new Lesson29Pool(
                3, 8,
                1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(100));
        pool.execute(service01);
        pool.shutdown();


        ExecutorService cachedService = Executors.newCachedThreadPool();
        cachedService.execute(() -> {
            System.out.println("Cached pool Fast Task");
        });
        cachedService.shutdown();

        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        Future<String> taskResultContainer = singlePool.submit(new RequestTask());

        // while (!taskResultContainer.isDone()){
        // какие-то действия основного потока
        // }

        String result = null;
        try {
            // result = taskResultContainer.get();
            result = taskResultContainer.get(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            System.out.println("у ожидающего потока был вызван метод interrupt");
        } catch (ExecutionException e) {
            System.out.println("во время выполнения задачи было выброшено исключение");
        } catch (TimeoutException e) {
            System.out.println("main поток не смог дождаться завершения задачи");
        }
        System.out.println(result);

        List<Future<String>> results = null;
        try {
            results = singlePool.invokeAll(
                    Arrays.asList(new RequestTask(), new RequestTask()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Future<String> future : results) {
            try {
                String res = future.get(11_000, TimeUnit.MILLISECONDS);
                System.out.println(res);
            } catch (InterruptedException |
                     ExecutionException |
                     TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
        singlePool.shutdown();

        // Executor
        // ExecutorService
        // ThreadPoolExecutor | ForkJoinPool
        // Executors

        int maxThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService stealingPool = Executors.newWorkStealingPool();
        stealingPool.execute(()->{});
        // ExecutorService stealingPool = Executors.newWorkStealingPool(3);
        // t1 -> taskqueue
        // t2 -> taskqueue
        // t3 -> taskqueue

        // и другие методы ExecutorService


    }
}
