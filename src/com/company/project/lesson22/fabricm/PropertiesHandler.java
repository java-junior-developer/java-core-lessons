package com.company.project.lesson22.fabricm;


public class PropertiesHandler extends AbstractHandler {

    public PropertiesHandler(String fileName) {
        super(fileName);
    }

    @Override
    public void write(String data) {
        System.out.println(data + "to PROPERTIES");
    }

}
