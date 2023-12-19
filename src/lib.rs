use robusta_jni::bridge;

#[bridge]
mod jni {
    use robusta_jni::convert::Signature;

    #[derive(Signature)]
    #[package(com.example.robusta)]
    struct Rust;

    impl Rust {
        pub extern "jni" fn toYaml(json: String) -> String {
            match serde_json::from_str::<serde_json::Value>(&json) {
                Ok(v) => match serde_yaml::to_string(&v) {
                    Ok(r) => r,
                    Err(_) => String::from("")
                },
                Err(_) => String::from("")
            }
        }

        pub extern "jni" fn toYamlBoom(json: String) -> String {
            let v = serde_json::from_str::<serde_json::Value>(&json).expect("Bad json");
            serde_yaml::to_string(&v).expect("YAML conversion error")
        }
    }
}
