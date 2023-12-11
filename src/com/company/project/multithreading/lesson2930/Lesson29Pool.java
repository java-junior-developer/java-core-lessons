package com.company.project.multithreading.lesson2930;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Lesson29Pool extends ThreadPoolExecutor {
    public Lesson29Pool(int corePoolSize, // изначальный(минимальный) размер пула
                        int maximumPoolSize, // максимальный размер пула
                        long keepAliveTime, // время бездействия потока
                        TimeUnit unit, // ед. измерения keepAliveTime
                        BlockingQueue<Runnable> workQueue) { // очередь для задач
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) System.out.println(t.getMessage());
    }
}
