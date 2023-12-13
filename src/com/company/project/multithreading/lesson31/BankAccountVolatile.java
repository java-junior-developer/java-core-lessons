package com.company.project.multithreading.lesson31;


public class BankAccountVolatile {
    private volatile int balance;

    public void putMoney(int value) {
        this.balance += value;
    }

    public int getBalance() {
        return balance;
    }
}
