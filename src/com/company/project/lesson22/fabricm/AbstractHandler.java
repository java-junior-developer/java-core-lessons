package com.company.project.lesson22.fabricm;

abstract public class AbstractHandler implements Handler {
    private String fileName;

    public AbstractHandler(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
