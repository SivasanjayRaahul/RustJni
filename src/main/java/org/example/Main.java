package org.example;

public class Main {

    private static native String hello(String input);

    static {
        // This actually loads the shared object that we'll be creating.
        // The actual location of the .so or .dll may differ based on your
        // platform.
        System.load("/Users/sivasanjayraahulmohan/Documents/E4R/RustJni/jni/src/main/rust/rust_lib/target/release/librust_lib.dylib");
    }

    public static void main(String[] args) {
        String output = Main.hello("Sivasanjay");
        System.out.println(output);
    }
}