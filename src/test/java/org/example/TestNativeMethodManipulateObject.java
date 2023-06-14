package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNativeMethodManipulateObject {

    public void printTimeDifference(long startTime, long endTime, String type, int iterations) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) ((endTime - startTime) * 1000000) / iterations);
    }

    private DBdata dBdata = new DBdata("E4R", 5);

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
        printTimeDifference(startTime, endTime, " creating new java string through JNI function", 100000000);
        assertEquals("String from native call", actualValue);
    }

    @Test
    void shouldCallJavaMethodFromNativeLibrary() {
        DBdata dBdata = new DBdata("E4R", 100);
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.printObjectValue(dBdata);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "invoking a getter method of an object", 100000000);
    }


    @Test
    void shouldAccessValueOfArrayObjectsThroughGetMethod() {
        DBdata[] dBdata = new DBdata[10000000];
        int objectIterator = 0;
        while (objectIterator < 10000000) {
            dBdata[objectIterator] = new DBdata("E4R", 100);
            objectIterator++;
        }
        long startTime = System.currentTimeMillis();
        NativeInvocation.printObjectsValue(dBdata, 10000000);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "invoke get method of object array", 1);
    }

    @Test
    void shouldReturnHundredAsDataValueFromNativeWhenTheValueIsChangedInsideNativeCode() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getNewObjectValue(dBdata, 100);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "set and get field value", 100000000);
        assertEquals(100, dBdata.getValue());
    }

    @Test
    void shouldReturnHundredAsDataValueFromNativeWhenTheValueIsChangedInsideNativeCodeThroughSetterAndGetter() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getNewObjectValueThroughMethod(dBdata, 100);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "getter and setter and return", 100000000);
        assertEquals(100, dBdata.getValue());
    }
}
