package com.company.project.multithreading.lesson27.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductReader extends Thread {
    private final File file;
    private final ArrayList<Product> arrayList = new ArrayList<>();

    public ProductReader(File file) {
        this.file = file;
    }

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }

    @Override
    public void run() {
        try (Stream<String> stringStream = Files.lines(file.toPath())) {
            arrayList.addAll(stringStream.map(string -> {
                String[] strings = string.split("::");
                return new Product(
                        Integer.parseInt(strings[0]),
                        strings[1],
                        Integer.parseInt(strings[2]),
                        Integer.parseInt(strings[3]));
            }).collect(Collectors.toCollection(ArrayList::new)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
