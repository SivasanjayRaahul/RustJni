package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestJavaInvocation {

    private static JavaInvocation javaInvocation;

    @BeforeAll
    public static void setJavaInvocation() {
        javaInvocation = new JavaInvocation();
    }

    public void printTimeDifference(long startTime, long endTime, String type) {
        System.out.println("timeTaken for passing " + type + " to java method in nano sec: " + (float) (endTime - startTime) / 100);
    }

    @Test
    void shouldPassADoubleValue() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passDouble(12.1111347731927391381029);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Double");
    }

    @Test
    void shouldPassEightDoubleValues() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passDouble(12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Eight Doubles");
    }

    @Test
    void shouldPassAStringValue() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passString("12.1111347731927391381029");
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "String");
    }

    @Test
    void shouldPassEightStringValues() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passString("12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029");
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Eight Strings");
    }

    @Test
    void shouldPassAnObject() {
        DBdata dBdata = new DBdata("Siva", 100);
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passObject(dBdata);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Object");
    }

    @Test
    void shouldPassEightObjects() {
        DBdata dBdata = new DBdata("Siva", 100);
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passObject(dBdata, dBdata, dBdata, dBdata, dBdata, dBdata, dBdata, dBdata);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Eight Objects");
    }

    @Test
    void shouldPassAnObjectArray() {
        DBdata[] dBdataArray = new DBdata[100000];
        int objectsIterator = 0;
        while (objectsIterator < 100000) {
            dBdataArray[objectsIterator] = new DBdata("Siva", 100);
            objectsIterator++;
        }
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            javaInvocation.passObjectArray(dBdataArray);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Objects Array");
    }
}