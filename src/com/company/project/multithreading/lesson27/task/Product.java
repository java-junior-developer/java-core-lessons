package com.company.project.multithreading.lesson27.task;

public class Product {
    private final int id;
    private final String name;
    private final int price;
    private final int count;

    public Product(int id, String name, int price, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getCount() {
        return count;
    }
}
