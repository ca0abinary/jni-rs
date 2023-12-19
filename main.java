package com.example.robusta;

import java.util.concurrent.*;

class Rust {
    private static native String toYaml(String json);
    private static native String toYamlBoom(String json);

    static {
        System.loadLibrary("jni_test");
    }

    public static void main(String[] args) {
        var input = """
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

        var output = "";    
        if (args.length > 0) {
            switch (args[0]) {
                case "fail":
                    input = "💩";
                    output = Rust.toYaml(input);
                    break;
                case "boom":
                    input = "💩";
                    Rust.toYamlBoom(input);
                    break;
                default:
                    break;
            }
        } else {
            output = Rust.toYaml(input);
        }

        System.out.println("\nConverted JSON to YAML");

        System.out.println("Input JSON:");
        System.out.println(input);

        System.out.println("Output YAML:");
        System.out.println(output);
    }
}
