package com.company.project.lesson21.singleton;

import java.time.LocalDate;

public class Settings02 {

    // Реализация паттерна (жадная - eager)
    // создание объекта происходит по умолчанию, а не по требованию
    private static volatile Settings02 SETTINGS_02_INSTANCE = new Settings02();


    private Settings02() {}

    public static Settings02 getSettings() {
        return SETTINGS_02_INSTANCE;
    }
}