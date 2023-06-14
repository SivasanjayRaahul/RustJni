use jni::JNIEnv;
use jni::objects::{JClass, JIntArray, JObject, JObjectArray, JString, JValue, ReleaseMode};
use jni::sys::{jdouble, jint, jobject, jsize};

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
    let output = env.new_string("String from native call").expect("Error while creating java string");
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
pub extern "system" fn Java_org_example_NativeInvocation_printObjectValue(mut env: JNIEnv,
                                                                          _class: JClass,
                                                                          currency: JObject) {
    let result = env.call_method(&currency, "getValue", "()I", &[]).expect("Error");
    let _value = result.i().unwrap();
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_printObjectsValue(mut env: JNIEnv,
                                                                           _class: JClass,
                                                                           currencies: JObjectArray,
                                                                           size: jint,
) {
    let mut data_pointer = 0;
    let mut result;
    while data_pointer < size {
        let element = env.get_object_array_element(&currencies, data_pointer).unwrap();
        result = env.call_method(&element, "getValue", "()I", &[]).expect("Error").i().unwrap();
        data_pointer = data_pointer + 1;
    }
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getModifiedIntArray(mut env: JNIEnv,
                                                                             _class: JClass,
                                                                             numbers: JIntArray,
                                                                             size: jint) {
    println!("{:?}", numbers);
    let mut arr_position: usize = 0;
    let mut element = unsafe { env.get_array_elements(&numbers, ReleaseMode::CopyBack).unwrap() };
    while arr_position < size as usize {
        element[arr_position] = 2;
        arr_position = arr_position + 1;
    }
}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getSameIntArray(mut env: JNIEnv,
                                                                         _class: JClass,
                                                                         numbers: JIntArray,
                                                                         size: jint) {
    println!("{:?}", numbers);
    let mut arr_position: usize = 0;
    let mut element = unsafe { env.get_array_elements(&numbers, ReleaseMode::NoCopyBack).unwrap() };
    while arr_position < size as usize {
        element[arr_position] = 2;
        arr_position = arr_position + 1;
    }
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getNewObjectValue(mut env: JNIEnv,
                                                                           _class: JClass,
                                                                           data_obj: JObject,
                                                                           value: jint) -> jint {
    env.set_field(&data_obj, "value", "I", JValue::from(value)).expect("Error setting field value");
    env.get_field(&data_obj, "value", "I").expect("Error getting value").i().unwrap()
}

#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getNewObjectValueThroughMethod(mut env: JNIEnv,
                                                                                        _class: JClass,
                                                                                        data_obj: JObject,
                                                                                        value: jint) -> jint {
    env.call_method(&data_obj, "setValue", "(I)V", &[JValue::from(value)]).expect("Error");
    env.call_method(&data_obj, "getValue", "()I", &[]).expect("Error").i().unwrap()
}


#[no_mangle]
pub extern "system" fn Java_org_example_NativeInvocation_getDouble(_env: JNIEnv,
                                                                   _class: JClass,
) -> jdouble {
    1.1234
}

#[no_mangle]
pub unsafe extern "system" fn Java_org_example_NativeInvocation_getObject(mut env: JNIEnv,
                                                                          _class: JClass,
                                                                          class_name: JString,
                                                                          key: JString,
                                                                          value: jint,
) -> jobject {
    let class_str: String = env.get_string(&class_name).unwrap().into();
    let class_value = env.find_class("org/example/".to_string() + class_str.as_str()).expect("Error getting class");
    let key_object = JObject::from_raw(key.as_raw());
    env.new_object(class_value, "(Ljava/lang/String;I)V", &[JValue::Object(&key_object), JValue::Int(value)]).unwrap().into_raw()
}

#[no_mangle]
pub unsafe extern "system" fn Java_org_example_NativeInvocation_getObjects(mut env: JNIEnv,
                                                                           _class: JClass,
                                                                           class_name: JString,
                                                                           key: JString,
                                                                           value: jint,
                                                                           no_of_objects: jint,
) -> jobject {
    let mut object_count: jint = 0;

    let class_str: String = env.get_string(&class_name).unwrap().into();
    let class = env.find_class("org/example/".to_string() + class_str.as_str()).expect("Error getting class");

    let object_array: JObjectArray = env.new_object_array(no_of_objects, &class, JObject::default()).expect("Error creating object array in rust");
    let key_object = JObject::from_raw(key.as_raw());

    let mut objects: Vec<JObject> = Vec::new();
    while object_count < no_of_objects {
        objects.push(env.new_object(&class, "(Ljava/lang/String;I)V", &[JValue::Object(&key_object), JValue::Int(value)]).unwrap());
        object_count = object_count + 1;
    }
    object_count = 0;
    while object_count < no_of_objects {
        env.set_object_array_element(&object_array, object_count, objects.get(object_count as usize).expect("Error getting object from array")).expect("Error pushing object into array");
        object_count = object_count + 1;
    }
    object_array.as_raw()
}
