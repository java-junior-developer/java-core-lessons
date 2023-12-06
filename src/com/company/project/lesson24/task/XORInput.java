package com.company.project.lesson24.task;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XORInput extends FilterInputStream {
    private byte[] key;

    public XORInput(InputStream in, String key) {
        super(in);
        this.key = key.getBytes();
    }

    @Override
    public int read(byte[] arrayToReadIn) throws IOException {
        int read = super.read(arrayToReadIn);
        for (int i = 0; i < arrayToReadIn.length; i++) {
            arrayToReadIn[i] = (byte) (arrayToReadIn[i] ^ key[i % key.length]);
        }
        return read;
    }
}
