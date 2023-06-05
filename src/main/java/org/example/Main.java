package org.example;

public class Main {


    private static native void printNumber(int number);

    private static native void printBoolean(boolean value);

    private static native void printByte(byte value);

    private static native void printShort(short value);

    private static native void printLong(long value);

    private static native void printFloat(float value);

    private static native void printDouble(double value);

    private static native void printString(String value);

    private static native String printLowerString(String value);

    private static native void printObject(Database database);

    private static native void printNumbers(int[] numbers);

    private static native void printObjects(Database[] currencies);

    static {
        System.load("/Users/sivasanjayraahulmohan/Desktop/rust/RustJni/jni/src/main/rust/rust_lib/target/release/librust_lib.dylib");
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.passNumber();
        main.passBoolean();
        main.passByte();
        main.passShort();
        main.passLong();
        main.passFloat();
        main.passDouble();

        main.passString();
        main.toLower();

        main.passObject();
        main.passObjects();

        main.passNumbers();

        main.directInvocation();
    }

    private void directInvocation() {
        Database[] database = new Database[100000];
        int currencyNumber = 0;
        while (currencyNumber < 100000) {
            database[currencyNumber] = new Database("Siva", currencyNumber);
            currencyNumber++;
        }
        System.out.println("Direct Object Invocation:");
        currencyNumber = 0;
        long startTime = System.currentTimeMillis();
        while (currencyNumber < 100000) {
            System.out.println(database[currencyNumber].getValue());
            currencyNumber++;
        }
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }


    private void passByte() {
        System.out.println("Byte:");
        long startTime = System.currentTimeMillis();
        Main.printByte((byte) -128);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passShort() {
        System.out.println("Short:");
        long startTime = System.currentTimeMillis();
        Main.printShort((short) 32767);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }


    private void passNumber() {
        System.out.println("Integer:");
        long startTime = System.currentTimeMillis();
        Main.printNumber(2147483647);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passBoolean() {
        System.out.println("Boolean:");
        long startTime = System.currentTimeMillis();
        Main.printBoolean(true);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passLong() {
        System.out.println("Long:");
        long startTime = System.currentTimeMillis();
        Main.printLong(-9223372036854775808L);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passFloat() {
        System.out.println("Float:");
        long startTime = System.currentTimeMillis();
        Main.printFloat(12.111134F);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passDouble() {
        System.out.println("Double:");
        long startTime = System.currentTimeMillis();
        Main.printDouble(12.1111347731927391381029);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }


    private void passString() {
        System.out.println("String:");
        long startTime = System.currentTimeMillis();
        String value = "E4R";
        Main.printString(value);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passObject() {
        Database database = new Database("Rupee", 1);
        System.out.println("Object:");
        long startTime = System.currentTimeMillis();
        Main.printObject(database);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passNumbers() {
        System.out.println("Integer Array:");
        int[] arr = new int[1000000];
        long startTime = System.currentTimeMillis();
        Main.printNumbers(arr);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }


    private void passObjects() {
        Database[] database = new Database[100000];
        int currencyNumber = 0;
        while (currencyNumber < 100000) {
            database[currencyNumber] = new Database("Siva", currencyNumber);
            currencyNumber++;
        }
        System.out.println("Objects:");
        long startTime = System.currentTimeMillis();
        Main.printObjects(database);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void toLower() {
        System.out.println("String Lower:");
        long startTime = System.currentTimeMillis();
        System.out.println(Main.printLowerString("E4R"));
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private static void printTimeDifference(long startTime, long endTime) {
        System.out.println("timeTaken in milli sec: " + (endTime - startTime));
    }
}