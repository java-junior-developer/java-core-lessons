package com.company.project.multithreading.lesson2930;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed {
    
    private Runnable action;
    private LocalDateTime time; 

    public Task(Runnable action, LocalDateTime time) {
        this.action = action;
        this.time = time;
    }

    public Runnable getAction() {
        return action;
    }

    @Override // take
    public long getDelay(TimeUnit unit) {
        return unit.convert(
                Duration.between(LocalDateTime.now(), time).getSeconds(),
                TimeUnit.SECONDS);
    }

    
    @Override // put
    public int compareTo(Delayed o) {
        LocalDateTime other = ((Task) o).time;
        return this.time.compareTo(other);
    }
}