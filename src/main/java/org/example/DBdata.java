package org.example;

public class DBdata {

    private final String key;
    private final int value;

    public DBdata(String key, int value) {
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
