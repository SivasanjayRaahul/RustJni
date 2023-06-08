cd src/main/java
javac -h org/example org/example/NativeInvocation.java
cd ../rust/rust_lib
cargo build --release