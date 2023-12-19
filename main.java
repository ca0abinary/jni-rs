package com.example.robusta;

import java.util.*;

class Rust {
    private static native String toYaml(String json);

    static {
        System.loadLibrary("jni_test");
    }

    public static void main(String[] args) {
        String input = """
        {
            "hello": "world",
            "some_int": 123,
            "some_double": 12.3,
            "complex_type_array": [{
                "id": 1,
                "name": "John Doe",
                "occupations": ["computer programmer"]
            }, {
                "id": 2,
                "name": "Jane Doe"
            }]
        }
        """;
        String output = Rust.toYaml(input);

        System.out.println("Converted JSON to YAML");

        System.out.println("Input JSON:");
        System.out.println(input);

        System.out.println("Output YAML:");
        System.out.println(output);
    }
}
