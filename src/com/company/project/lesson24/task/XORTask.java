package com.company.project.lesson24.task;

import java.io.*;

public class XORTask {

    public static void main(String... s) {
        String key = "string key";
        String beforeString = "...some text...";
        String afterString = null;

        try (FileOutputStream fileOutputStream = new FileOutputStream("xor.txt");
             XOROutput xorOutput = new XOROutput(fileOutputStream, key)) {

            xorOutput.write(beforeString.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileInputStream fileInputStream = new FileInputStream("xor.txt");
             XORInput xorInput = new XORInput(fileInputStream, key)) {

            byte[] bytes = new byte[fileInputStream.available()];
            xorInput.read(bytes);

            afterString = new String(bytes);

            System.out.println(beforeString.equals(afterString));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}