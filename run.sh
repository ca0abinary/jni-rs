#!/usr/bin/env bash
set -e

cargo build
$JAVA_HOME/bin/java -Djava.library.path=$(pwd)/target/debug main.java $@
