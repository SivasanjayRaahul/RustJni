package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRustToJavaReturn {

    public void printTimeDifference(long startTime, long endTime, String type) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) (endTime - startTime) / 100);
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
        printTimeDifference(startTime, endTime, "Get Double");
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
        printTimeDifference(startTime, endTime, " new string through JNI function");
        assertEquals("String from native call", NativeInvocation.getString());
    }
}
