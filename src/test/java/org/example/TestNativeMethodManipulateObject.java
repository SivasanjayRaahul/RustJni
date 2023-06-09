package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNativeMethodManipulateObject {

    public void printTimeDifference(long startTime, long endTime, String type) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) (endTime - startTime) / 100);
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
    void shouldReturnHundredAsDataValueFromNativeWhenTheValueIsChangedInsideNativeCode() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getNewObjectValue(dBdata, 100);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "set and get field value");
        assertEquals(100, dBdata.getValue());
    }
}
