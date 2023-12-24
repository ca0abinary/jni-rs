#[robusta_jni::bridge]
mod jni {
    use robusta_jni::convert::Signature;

    #[derive(Signature)]
    #[package(org.tempuri.jd)]
    struct Rust;

    impl Rust {
        pub extern "jni" fn x12TransformFormat(x12: String) -> String {
            match edi::parse(&x12) {
                Ok(v) => match serde_json::to_string(&v) {
                    Ok(r) => r,
                    Err(_) => String::from("Conversion fail")
                },
                Err(e) => String::from(format!("Parse fail: {e}"))
            }
        }

        pub extern "jni" fn goBoom(json: String) -> String {
            let v = serde_json::from_str::<serde_json::Value>(&json).expect("Bad input");
            serde_yaml::to_string(&v).expect("Conversion error")
        }
    }
}
