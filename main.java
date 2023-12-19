package com.example.robusta;

import java.io.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class Rust {
    private static native String toYaml(String json);
    private static native String toYamlBoom(String json);

    static {
        System.loadLibrary("jni_test");
    }

    public static void main(String[] args) {
        var output = "";
        var input = getStdIn();
        var mode = (args.length > 0) ? args[0] : "";

        switch (mode) {
            case "fail":
                input = "💩";
                output = Rust.toYaml(input);
                break;
            case "boom":
                input = "💩";
                Rust.toYamlBoom(input);
                break;
            default:
                output = Rust.toYaml(input);
                break;
        }

        System.out.println("\nConverted JSON to YAML");

        System.out.println("Input JSON:");
        System.out.println(input);

        System.out.println("Output YAML:");
        System.out.println(output);
    }

    public static String getStdIn() {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.lines().collect(Collectors.joining());
    }
}
