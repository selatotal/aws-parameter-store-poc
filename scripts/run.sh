#!/bin/bash

scripts/build.sh
./gradlew :aws-parameter-store-poc-war:appRun
