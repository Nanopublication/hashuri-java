#!/bin/bash

cd "$( dirname "${BASH_SOURCE[0]}" )"
cd ..

mvn -q -e exec:java -Dexec.mainClass="ch.tkuhn.hashuri.file.ProcessFile" -Dexec.args="$*"
