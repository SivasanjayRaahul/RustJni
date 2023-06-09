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
        printTimeDifference(startTime, endTime, " new string through JNI function");
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
        printTimeDifference(startTime, endTime, "invoking a getter method");
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
        NativeInvocation.printObjects(dBdata, 10000000);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "invoke method of object array");
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

    @Test
    void shouldReturnHundredAsDataValueFromNativeWhenTheValueIsChangedInsideNativeCodeThroughSetterAndGetter() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getNewObjectValueThroughMethod(dBdata, 100);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "getter and setter and return");
        assertEquals(100, dBdata.getValue());
    }
}
