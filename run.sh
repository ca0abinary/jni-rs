#!/usr/bin/env bash
set -e

JAVA_CMD=$(which java)
if [ -z "$JAVA_CMD" ]; then
    echo "Could not find java in PATH"
    exit 1;
fi

cargo build
cat test_data.json | $JAVA_CMD -Djava.library.path=$(pwd)/target/debug main.java $@
