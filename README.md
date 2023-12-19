# Run Rust code from Java

Demo of the really cool [robusta_jni](https://github.com/giovanniberti/robusta) project to enable running libraries created in Rust from Java. The goal is to demonstrate fast and memory safe code can easily be used by Java programs through the JNI ABI.

To run ensure `$JAVA_HOME` is set and execute
```sh
./run.sh
```
This command will build the rust library and invoke `main.java` with the correct `java` command to find the library.
