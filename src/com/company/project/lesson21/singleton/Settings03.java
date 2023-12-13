package com.company.project.lesson21.singleton;


public enum Settings03 {

    SETTINGS_03_INSTANCE;

    // Реализация паттерна (через перечисление)
    // enum гарантирует создание одного экземпляра в одно- и многопоточных средах
    // ленивая инициализация невозможна
    Settings03() {}

    public static Settings03 getSettings() {
        return SETTINGS_03_INSTANCE;
    }

}