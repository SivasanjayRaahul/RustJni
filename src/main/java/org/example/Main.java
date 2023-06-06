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

    private static native void printObject(DBdata DBdata);

    private static native void printNumbers(int[] numbers, int size);

    private static native void printObjects(DBdata[] currencies, int size);

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
        main.passObjects(100000);

        main.passNumbers(100000);

        main.directInvocation(100000);

    }


    private void directInvocation(int size) {
        DBdata[] currency = new DBdata[size];
        int currencyNumber = 0;
        while (currencyNumber < size) {
            currency[currencyNumber] = new DBdata("Siva", currencyNumber);
            currencyNumber++;
        }
        System.out.println("Direct Object Invocation:");
        currencyNumber = 0;
        long startTime = System.currentTimeMillis();
        while (currencyNumber < 100000) {
            System.out.println(currency[currencyNumber].getValue());
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
        DBdata DBdata = new DBdata("Rupee", 1);
        System.out.println("Object:");
        long startTime = System.currentTimeMillis();
        Main.printObject(DBdata);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }

    private void passNumbers(int size) {
        System.out.println("Integer Array:");
        int[] arr = new int[size];
        int arrPosition = 0;
        while (arrPosition < size) {
            arr[arrPosition] = arrPosition;
            arrPosition++;
        }
        long startTime = System.currentTimeMillis();
        Main.printNumbers(arr, size);
        long endTime = System.currentTimeMillis();
        printTimeDifference(startTime, endTime);
    }


    private void passObjects(int size) {
        DBdata[] DBdata = new DBdata[size];
        int currencyNumber = 0;
        while (currencyNumber < size) {
            DBdata[currencyNumber] = new DBdata("Siva", currencyNumber);
            currencyNumber++;
        }
        System.out.println("Objects:");
        long startTime = System.currentTimeMillis();
        Main.printObjects(DBdata, size);
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