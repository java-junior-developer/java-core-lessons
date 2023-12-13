package com.company.project.lesson21.singleton;

import java.time.LocalDate;

public class Settings04 {

    // Реализация паттерна (через вложенный класс)
    // гарантирует создание одного экземпляра в одно- и многопоточных средах
    // используется ленивая инициализация
    private Settings04() {}

    private static class Settings04Holder { // загрузка по требованию
        private static final Settings04 SETTINGS_04_INSTANCE = new Settings04();
    }

    public static Settings04 getSettings() {
        return Settings04Holder.SETTINGS_04_INSTANCE;
    }
}


