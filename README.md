# Run Rust code from Java

Demo of the really cool [robusta_jni](https://github.com/giovanniberti/robusta) project to enable running libraries created in Rust from Java. The goal is to demonstrate fast and memory safe code can easily be used by Java programs through the JNI ABI.

To run ensure `$JAVA_HOME` is set and execute

```sh
./run.sh
```

This command will build the rust library and invoke `main.java` with the correct `java` command to find the library.

To test bad data simply pass fail after `./run.sh`:

```sh
./run.sh fail
```

This will pass bad JSON to the rust function, but since the rust function is written safely it will not crash. 

You must write good code or your thread will panic. To see this run

```sh
./run.sh boom
```

It is not possible as far as I can tell to wrap the native call, so you must write perfect native interfaces or have another way to handle those crashes. Rust was chosen to demonstrate the native interface in this codebase because the language and compiler work together to help reduce the chances of bad code.
