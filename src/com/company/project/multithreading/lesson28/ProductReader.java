package com.company.project.multithreading.lesson28;

import java.io.File;
import java.util.List;
public class ProductReader extends Thread{
    private List<Product> products;
    private File file;

    public ProductReader(List<Product> products, File file) {
        this.products = products;
        this.file = file;
    }

    @Override
    public void run() {

    }
}
