use jni::JNIEnv;
use jni::objects::{JClass, JObject, JObjectArray, JString};
use jni::sys::{jboolean, jbyte, jdouble, jfloat, jint, jintArray, jlong, jshort};

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printNumber(_env: JNIEnv,
                                                         _class: JClass,
                                                         number: jint) {
    println!("{}", number);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printBoolean(_env: JNIEnv,
                                                          _class: JClass,
                                                          value: jboolean) {
    println!("{}", value);
}


#[no_mangle]
pub extern "system" fn Java_org_example_Main_printByte(_env: JNIEnv,
                                                       _class: JClass,
                                                       value: jbyte) {
    println!("{}", value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printShort(_env: JNIEnv,
                                                        _class: JClass,
                                                        value: jshort) {
    println!("{}", value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printLong(_env: JNIEnv,
                                                       _class: JClass,
                                                       value: jlong) {
    println!("{}", value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printFloat(_env: JNIEnv,
                                                        _class: JClass,
                                                        value: jfloat) {
    println!("{}", value);
}


#[no_mangle]
pub extern "system" fn Java_org_example_Main_printDouble(_env: JNIEnv,
                                                         _class: JClass,
                                                         value: jdouble) {
    println!("{}", value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printString(mut env: JNIEnv,
                                                         _class: JClass,
                                                         value: JString) {
    let string_value: String = env.get_string(&value).expect("Error converting string").into();
    println!("{}", string_value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_Main_printLowerString<'a>(mut env: JNIEnv<'a>,
                                                                  _class: JClass,
                                                                  value: JString<'a>) -> JString<'a> {
    let string_value: String = env.get_string(&value).expect("Error converting string").into();
    let lower_string = string_value.to_lowercase();
    let output = env.new_string(lower_string).expect("Error while creating lowercase string");
    output
}


#[no_mangle]
pub extern "system" fn Java_org_example_Main_printObject(mut env: JNIEnv,
                                                         _class: JClass,
                                                         currency: JObject) {
    let result = env.call_method(&currency, "getValue", "()I", &[]).expect("Error");
    println!("{:?}", result.i().unwrap());
    println!("{:?}", currency);
}


#[no_mangle]
pub extern "system" fn Java_org_example_Main_printObjects(mut env: JNIEnv,
                                                          _class: JClass,
                                                          currencies: JObjectArray) {
    let mut currency_number = 0;
    let mut result;
    while currency_number < 100000 {
        let element = env.get_object_array_element(&currencies, currency_number).unwrap();
        result = env.call_method(&element, "getValue", "()I", &[]).expect("Error");
        println!("{:?}", result.i().unwrap());
        currency_number = currency_number + 1;
    }
}


#[no_mangle]
pub extern "system" fn Java_org_example_Main_printNumbers(_env: JNIEnv,
                                                          _class: JClass,
                                                          numbers: jintArray) {
    println!("{:?}", numbers);
}