package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestNativeMethodArgumentPass {
    public void printTimeDifference(long startTime, long endTime, String type) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) (endTime - startTime) / 100);
    }

    @Test
    void shouldPassADoubleValue() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.passDouble(12.1111347731927391381029);
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
            NativeInvocation.passDouble(12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029, 12.1111347731927391381029);
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
            NativeInvocation.passString("12.1111347731927391381029");
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
            NativeInvocation.passString("12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029", "12.1111347731927391381029");
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
            NativeInvocation.passObject(dBdata);
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
            NativeInvocation.passObject(dBdata, dBdata, dBdata, dBdata, dBdata, dBdata, dBdata, dBdata);
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
            NativeInvocation.passObjectArray(dBdataArray);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Objects Array");
    }

    @Test
    void shouldReturnStringValue() {
        int iterations = 0;
        String actualValue = "";
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            actualValue = NativeInvocation.getString();
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "getting a string");
        assertEquals("String from native call", actualValue);
    }

    @Test
    void shouldCallJavaMethodFromNativeLibrary() {
        DBdata dBdata = new DBdata("E4R", 100);
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.printObject(dBdata);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "invoking a java method");
    }

    @Test
    void shouldPassDataObjectArray() {
        DBdata[] dBdata = new DBdata[1000000];
        int objectIterator = 0;
        while (objectIterator < 1000000) {
            dBdata[objectIterator] = new DBdata("E4R", 100);
            objectIterator++;
        }
        long startTime = System.currentTimeMillis();
        NativeInvocation.printObjects(dBdata, 1000000);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "invoke method of objects");
    }

}
