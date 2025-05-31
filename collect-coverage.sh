#!/bin/bash

set -e

echo "🔁 Cleaning and building with JaCoCo agent..."
mvn clean verify

echo "🚀 Starting Spring Boot app with JaCoCo agent..."
JACOCO_AGENT="-javaagent:${HOME}/.m2/repository/org/jacoco/org.jacoco.agent/0.8.11/org.jacoco.agent-0.8.11-runtime.jar=destfile=target/jacoco.exec,output=tcpserver,address=*,port=6300"


java $JACOCO_AGENT -jar target/*.jar &
APP_PID=$!

echo "⏳ Waiting for app to start..."
sleep 5

echo "✅ Running Karate tests..."
mvn test -Dtest=features.KarateTest

echo "📡 Sending curl traffic..."
curl -s http://localhost:8080/hello/formal > /dev/null
curl -s http://localhost:8080/hello/informal > /dev/null

echo "📥 Dumping JaCoCo exec data from running app..."
mvn -f jacoco-dump.xml jacoco:dump

echo "🧾 Generating JaCoCo report..."
mvn jacoco:report -DdataFile=target/jacoco.exec

echo "🧹 Shutting down Spring Boot app..."
kill $APP_PID

echo "✅ Done! Coverage report available at: target/site/jacoco/index.html"
