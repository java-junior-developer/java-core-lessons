package com.company.project.multithreading.lesson2930;

import java.util.UUID;
import java.util.concurrent.Callable;

public class RequestTask implements Callable<String> {

    @Override
    public String call() /* throws Exception */ {
        return makeRequest();
    }

    private String makeRequest() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
            return UUID.randomUUID().toString();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
