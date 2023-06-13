package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestRustToJavaReturn {

    public void printTimeDifference(long startTime, long endTime, String type, int iterations) {
        System.out.println("timeTaken for " + type + " in nano sec: " + (float) ((endTime - startTime) * 1000000) / iterations);
    }

    @Test
    void shouldReturnTheModifiedIntArray() {
        int[] arr = new int[3];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        long startTime = System.currentTimeMillis();
        NativeInvocation.getModifiedIntArray(arr, 3);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Get modified int array", 1);
        System.out.println("Modified array: " + Arrays.toString(arr));
        assertEquals(arr[0], 2);
    }

    @Test
    void shouldReturnTheSameIntArrayEvenWhenModified() {
        int[] arr = new int[3];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        long startTime = System.currentTimeMillis();
        NativeInvocation.getSameIntArray(arr, 3);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Get same int array even though modified", 1);
        System.out.println("Modified array: " + Arrays.toString(arr));
        assertNotEquals(arr[0], 2);
        assertEquals(arr[0], 1);
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
        assertEquals(dBdata.getValue(), 100);
    }

    @Test
    void shouldReturnArrayOfJavaObjectsFromRust() {
        long startTime = System.currentTimeMillis();
        DBdata[] dBdataArr = NativeInvocation.getObjects("DBdata", "E4R", 100, 10000000);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime, "Array of Java Objects from rust", 1);
        assertEquals(((DBdata) dBdataArr[0]).getValue(), 100);
    }
}
