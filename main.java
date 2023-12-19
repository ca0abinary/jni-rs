package com.example.robusta;

import java.util.*;
import java.util.concurrent.*;

class Rust {
    private static native String toYaml(String json);
    private static native String toYamlBoom(String json);

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

        String output = "";    
        if (args.length > 0) {
            switch (args[0]) {
                case "fail":
                    input = input + "💩";
                    output = Rust.toYaml(input);
                    break;
                case "boom":
                    boom();
                    break;
                case "wrapped":
                    wrapped();
                    break;
                default:
                    output = "unknown argument passed";
            }
        }

        System.out.println("\nConverted JSON to YAML");

        System.out.println("Input JSON:");
        System.out.println(input);

        System.out.println("Output YAML:");
        System.out.println(output);
    }

    public static void boom() {
        Rust.toYamlBoom("💩");
    }

    public static void wrapped() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Rust.toYamlBoom("💩");
                return null;
            }
        });

        try {
            future.get();
        } catch (ExecutionException e) {
            System.out.println("** RuntimeException from thread ");
            e.getCause().printStackTrace(System.out);
        } catch (InterruptedException e) {
            ; // pass
        }

        executor.shutdown();
    }
}
