package com.company.project.lesson24.task;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class XOROutput extends FilterOutputStream {
    private byte[] key;
    public XOROutput(OutputStream out, String key) {
        super(out);
        this.key = key.getBytes();
    }

    @Override
    public void write(byte[] bytesToWrite) throws IOException {
        for (int i = 0; i < bytesToWrite.length; i++) {
            bytesToWrite[i] = (byte) (bytesToWrite[i] ^ key[i % key.length]);
        }
        super.write(bytesToWrite);
    }
}
