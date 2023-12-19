use robusta_jni::bridge;

#[bridge]
mod jni {
    use robusta_jni::convert::Signature;

    #[derive(Signature)]
    #[package(com.example.robusta)]
    struct Rust;

    impl Rust {
        pub extern "jni" fn toYaml(json: String) -> String {
            let json_value: serde_json::Value = serde_json::from_str(&json).expect("JSON parsing failed");
            serde_yaml::to_string(&json_value).expect("YAML conversion failed")
        }
    }
}
