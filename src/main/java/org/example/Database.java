package org.example;

public class Database {

    private final String key;
    private final int value;

    public Database(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
