package com.company.project.multithreading.lesson31;

import java.util.concurrent.atomic.AtomicInteger;
// Атомарная операция - операция, которую можно безопасно выполнять
// при параллельных вычислениях в нескольких потоках,
// не используя этом ни блокировок, ни синхронизаций

// Если нужно просто изменять одну переменную несколькими потоков,
// лучше выбирать классы с поддержкой атомарных операций над примитивами.
// Виртуальная машина в большинстве случаев
// использует атомарную операцию процессора. Поэтому они быстрее синхронизации.
// Они содержат арифметические методы,
// которые применяются к целочисленным атомарным переменным.
// updateAndGet / getAndUpdate - для увеличения значения
// getAndSet - для установки нового значения
// compareAndSet - для сравнения значений
// getAndIncrement / incrementAndGet - для увеличения значения на 1
// getAndDecrement / decrementAndGet - для уменьшения значение на 1
// и т.д.

public class BankAccountAtomic {
    private final AtomicInteger balance = new AtomicInteger(0);

    public void putMoney(int value) {
        this.balance.updateAndGet(currentBalance -> currentBalance + value);
    }

    public int getBalance() {
        return balance.get();
    }
}