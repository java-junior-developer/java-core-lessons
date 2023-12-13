package com.company.project.multithreading.lesson31;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Lesson31 {

    public static void main(String[] args) {


        BankAccountVolatile accountVolatile = new BankAccountVolatile();
        ArrayList<Thread> threadsVolatile = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadsVolatile.add(new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    accountVolatile.putMoney(10);
                }
            }));
        }

        threadsVolatile.forEach(Thread::start);
        for (Thread thread : threadsVolatile) {
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        }
        System.out.println(accountVolatile.getBalance()); //

        BankAccountAtomic accountAtomic = new BankAccountAtomic();
        ArrayList<Thread> threadsAtomic = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadsAtomic.add(new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    accountAtomic.putMoney(10);
                }
            }));
        }

        threadsAtomic.forEach(Thread::start);
        for (Thread thread : threadsAtomic) {
            try {
                thread.join();
            } catch (InterruptedException e) {

            }
        }
        System.out.println(accountAtomic.getBalance());

        List<String> strings = new ArrayList<>();
        List<String> strings1 = Collections.synchronizedList(strings);

    }

    // java 21
    private static void virtualThreads(){
        // Thread
        // виртуальные потоки
        // фоновые
        Thread virtual01 = Thread.startVirtualThread(()->{});
        Thread.startVirtualThread(()->{});
        Thread virtual02 = Thread
                .ofVirtual()
                .name("virtual-02")
                .unstarted(()->{});
        virtual02.start();
        ExecutorService virtualService = Executors.newVirtualThreadPerTaskExecutor();
    }
}
