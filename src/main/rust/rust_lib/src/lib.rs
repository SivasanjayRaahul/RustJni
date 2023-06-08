use jni::JNIEnv;
use jni::objects::{JClass, JIntArray, JObject, JObjectArray, JString, ReleaseMode};
use jni::sys::{jdouble, jint};

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passDouble__D(_env: JNIEnv,
                                                                       _class: JClass,
                                                                       _value: jdouble) {}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passDouble__DDDDDDDD(_env: JNIEnv,
                                                                              _class: JClass,
                                                                              _value_one: jdouble, _value_two: jdouble, _value_three: jdouble, _value_four: jdouble, _value_five: jdouble, _value_six: jdouble, _value_seven: jdouble, _value_eight: jdouble) {}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passString__Ljava_lang_String_2(_env: JNIEnv,
                                                                                         _class: JClass,
                                                                                         _value: JString) {}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passString__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2(_env: JNIEnv, _class: JClass, _value_one: JString, _value_two: JString, _value_three: JString, _value_four: JString, _value_five: JString, _value_six: JString, _value_seven: JString, _value_eight: JString) {}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passObject__Lorg_example_DBdata_2(_env: JNIEnv,
                                                                                           _class: JClass,
                                                                                           _db_data: JObject) {}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passObject__Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2Lorg_example_DBdata_2
(_env: JNIEnv,
 _class: JClass,
 _db_data: JObject, _db_data_one: JObject, _db_data_two: JObject, _db_data_three: JObject, _db_data_four: JObject, _db_data_five: JObject, _db_data_seven: JObject, _db_data_eight: JObject) {}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_passObjectArray(_env: JNIEnv,
                                                                         _class: JClass,
                                                                         _db_array: JObjectArray) {}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getString<'a>(env: JNIEnv<'a>,
                                                                           _class: JClass<'a>,
) -> JString<'a> {
    let output = env.new_string("String from native call").expect("Error while creating lowercase string");
    output
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printString(mut env: JNIEnv,
                                                                     _class: JClass,
                                                                     value: JString) {
    let string_value: String = env.get_string(&value).expect("Error converting string").into();
    println!("{}", string_value);
}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printLowerString<'a>(mut env: JNIEnv<'a>,
                                                                              _class: JClass,
                                                                              value: JString<'a>) -> JString<'a> {
    let string_value: String = env.get_string(&value).expect("Error converting string").into();
    let lower_string = string_value.to_lowercase();
    let output = env.new_string(lower_string).expect("Error while creating lowercase string");
    output
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printObject(mut env: JNIEnv,
                                                                     _class: JClass,
                                                                     currency: JObject) {
    let result = env.call_method(&currency, "getValue", "()I", &[]).expect("Error");
    println!("{:?}", result.i().unwrap());
    println!("{:?}", currency);
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printObjects(mut _env: JNIEnv,
                                                                      _class: JClass,
                                                                      _currencies: JObjectArray,
) {
    // let mut currency_number = 0;
    // let mut result;
    // while currency_number < size {
    //     let element = env.get_object_array_element(&currencies, currency_number).unwrap();
    //     result = env.call_method(&element, "getValue", "()I", &[]).expect("Error");
    //     println!("{:?}", result.i().unwrap());
    //     currency_number = currency_number + 1;
    // }
    // println!("{:?}", env.get_array_length(&currencies));
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printNumbers(mut env: JNIEnv,
                                                                      _class: JClass,
                                                                      numbers: JIntArray,
                                                                      size: jint) {
    println!("{:?}", numbers);
    let mut arr_position: usize = 0;
    let element = unsafe { env.get_array_elements(&numbers, ReleaseMode::CopyBack).unwrap() };
    while arr_position < size as usize {
        println!("{}", element[arr_position]);
        arr_position = arr_position + 1;
    }
}