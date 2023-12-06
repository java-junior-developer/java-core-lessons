package com.company.project.multithreading.lesson27.task;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Task02 {
    private List<ProductReader> readers;

    public Task02(File... files) {
        this.readers = new ArrayList<>(files.length);
        for (File file : files) {
            readers.add(new ProductReader(file));
        }
    }

    private void startReaders() {
        for (ProductReader reader : readers) {
            reader.start();
        }
    }

    private void joinReaders() {
        for (ProductReader reader : readers) {
            try {
                reader.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Map<String, TreeSet<Product>> byNamePrice() {
        startReaders();
        joinReaders();
        return readers.stream()
                .flatMap(reader -> reader.getArrayList().stream())
                .collect(Collectors.groupingBy(Product::getName,
                        Collectors.toCollection(TreeSet::new)));
    }

    public static void main(String[] args) {
        Thread thread01 = new Thread();
        Thread thread02 = new Thread();
        /*
        thread01.start();
        thread01.join();

        thread02.start();
        thread02.join();
        */
        thread01.start();
        thread02.start();

        try {
            thread01.join();
            thread02.join();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }



        Task02 task02 = new Task02(new File("file01.txt"));
        System.out.println(task02.byNamePrice());
    }
}
