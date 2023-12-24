package org.tempuri.jd;

import java.io.*;
import java.util.stream.Collectors;

class Rust {
    private static native String x12TransformFormat(String x12);
    private static native String goBoom(String json);

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
                output = Rust.x12TransformFormat(input);
                break;
            case "boom":
                input = "💩";
                Rust.goBoom(input);
                break;
            default:
                output = Rust.x12TransformFormat(input);
                break;
        }

        System.out.println("Input:");
        System.out.println(input);

        System.out.println("Output:");
        System.out.println(output);
    }

    public static String getStdIn() {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.lines().collect(Collectors.joining());
    }
}
