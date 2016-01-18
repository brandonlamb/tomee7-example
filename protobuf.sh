#!/bin/sh

#protoc --java_out=target/generated-sources src/main/resources/proto/players.proto
protoc --java_out=src/main/java src/main/resources/proto/players.proto
