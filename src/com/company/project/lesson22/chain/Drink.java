package com.company.project.lesson22.chain;

public class Drink extends Action{
    @Override
    protected void execute(Animal animal) {
        System.out.println("Drink");
    }
}
