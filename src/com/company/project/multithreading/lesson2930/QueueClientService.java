package com.company.project.multithreading.lesson2930;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueClientService implements Runnable{
    private ArrayBlockingQueue<String> tokens;

    public QueueClientService(ArrayBlockingQueue<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public void run() {
        // take() put()
        try {
            String token = tokens.take();
            String newToken = makeRequest(token);
            tokens.put(newToken);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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