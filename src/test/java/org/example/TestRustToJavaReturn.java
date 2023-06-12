package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRustToJavaReturn {

    public void printTimeDifference(long startTime, long endTime, String type, int iterations) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) ((endTime - startTime) * 1000000) / iterations);
    }

    @Test
    void shouldReturnDoubleFromRustWhenInvoked() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getDouble();
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Get Double", 100000000);
        assertEquals(NativeInvocation.getDouble(), 1.1234);
    }

    @Test
    void shouldReturnStringValue() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 100000000) {
            NativeInvocation.getString();
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, " new string through JNI function", 100000000);
        assertEquals("String from native call", NativeInvocation.getString());
    }

    @Test
    void shouldReturnAnObjectOfClassDBdata() {
        int iterations = 0;
        long startTime = System.currentTimeMillis();
        while (iterations < 10000000) {
            NativeInvocation.getObject("DBdata", "E4R", 100);
            iterations++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, " getObject", 10000000);
        DBdata dBdata = (DBdata) NativeInvocation.getObject("DBdata", "E4R", 100);
        System.out.println(dBdata.getValue());
    }

}
