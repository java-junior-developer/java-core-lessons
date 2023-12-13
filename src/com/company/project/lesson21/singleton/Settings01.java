package com.company.project.lesson21.singleton;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Settings01 implements Serializable {

    // Реализация паттерна (ленивая - lazy)
    // для однопоточных сред
    // использование в многопоточной среде может породить множество экземпляров
    private Settings01() {
        if (SETTINGS_01_INSTANCE != null) throw new IllegalStateException("Экземпляр уже создан");
    }

    private static Settings01 SETTINGS_01_INSTANCE;

    public static Settings01 getSettings() {
        if(SETTINGS_01_INSTANCE == null) SETTINGS_01_INSTANCE = new Settings01();
        return SETTINGS_01_INSTANCE;
    }

    // для многопоточных сред
    /*
    private static volatile Settings01 SETTINGS_01_INSTANCE = null;
    public static Settings01 getSettings() {
        if (SETTINGS_01_INSTANCE == null) {
            synchronized (Settings01.class) {
                SETTINGS_01_INSTANCE = new Settings01();
            }
        }
        return SETTINGS_01_INSTANCE;
    }
    */

    // для многопоточных сред (двойная проверка на созданный экземпляр)
    /*
    private static volatile Settings01 SETTINGS_01_INSTANCE = null;
    public static Settings01 getSettings() {
        if (SETTINGS_01_INSTANCE == null) {
            synchronized (Settings01.class) {
                if (SETTINGS_01_INSTANCE == null) {
                    SETTINGS_01_INSTANCE = new Settings01();
                }
            }
        }
        return SETTINGS_01_INSTANCE;
    }
    */

    @Override
    protected Object clone() {
        throw new IllegalStateException("Клонирование не поддерживается");
    }


    protected Object readResolve(){ // когда ObjectInputStream читает из потока, перед десериализацией
        return SETTINGS_01_INSTANCE;
    }
}