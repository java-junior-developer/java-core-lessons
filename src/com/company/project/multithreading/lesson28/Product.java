package com.company.project.multithreading.lesson28;

public record Product
        (String name, int price, int count) {
}

/*
class Product {
    private final String name;
    private final int price;
    private final int count;

    public Product(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }
    // getters
    // toString
    // equals + hashCode
}*/
