package org.example;

public class NativeInvocation {

    public static native void passDouble(double value);

    public static native void passDouble(double valueOne, double valueTwo, double valueThree, double valueFour, double valueFive, double valueSix, double valueSeven, double valueEight);

    public static native void passString(String value);

    public static native void passString(String valueOne, String valueTwo, String valueThree, String valueFour, String valueFive, String valueSix, String valueSeven, String valueEight);

    public static native void passObject(DBdata dBdata);

    public static native void passObject(DBdata dBdataOne, DBdata dBdataTwo, DBdata dBdataThree, DBdata dBdataFour, DBdata dBdataFive, DBdata dBdataSix, DBdata dBdataSeven, DBdata dBdataEight);

    public static native void passObjectArray(DBdata[] dBdata);

    public static native String getString();

    public static native void printObject(DBdata DBdata);

    public static native void printObjects(DBdata[] dBdata, int size);

    public static native int getNewObjectValue(DBdata dBdata, int value);

    private static native void printString(String value);

    private static native String printLowerString(String value);

    private static native void printNumbers(int[] numbers, int size);


    static {
        System.load("/home/e4r/workspace/RustJni/src/main/rust/rust_lib/target/release/librust_lib.so");
    }


}
