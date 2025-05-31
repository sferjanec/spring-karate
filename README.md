# Spring Boot + Karate API + JaCoCo Coverage Demo

This project demonstrates how to:

- Build a simple Spring Boot REST API
- Test it using [Karate DSL](https://karatelabs.io/)
- Capture code coverage using [JaCoCo](https://www.jacoco.org/jacoco/)
- Merge API test coverage and unit test coverage into one report

---

## 📦 Prerequisites

- Java 17 or 21
- Maven 3.6+
- Karate (bundled via Maven)
- JaCoCo Agent JAR: included in `jacoco-agent/`

---

## 🚀 How to Run the Project

### 1. Build the Application

```bash
mvn clean package
```

### 2. Start the server with JaCoCo coverage recording enabled via TCP:

### Keep this process running while you run tests.

```
java -javaagent:jacoco-agent/org.jacoco.agent-0.8.11-runtime.jar=output=tcpserver,address=*,port=6300 \
  -jar target/spring-karate-0.0.1-SNAPSHOT.jar
```

### 3: Run Karate API Tests

In a new terminal:

```
mvn test -Dtest=features.KarateTest
```

### 4: Dump JaCoCo Coverage from Running App

```
mvn jacoco:dump -Pdump-karate
```

This saves coverage data to:
target/jacoco-karate.exec

### 5: Merge Unit + Karate Coverage

```
mvn verify -Pmerge-coverage
```

This should create:
target/jacoco-merged.exec

### 6: Generate Final Report

```
mvn jacoco:report
```

### 7: Open the combined report under:

target/site/jacoco/index.html

### Additional Info:

The following steps were used to support API test coverage with Karate and merge it with unit test coverage:

1. Dump JaCoCo coverage data from the Spring Boot app while it's running (after Karate API tests run)

2. Merge multiple .exec files into a single report (jacoco.exec, jacoco-karate.exec, etc.)

Since these steps aren't part of the default maven lifecycle, I added two custom profiles:

- dump-karate
  This dumps code coverage from the live Spring Boot app after Karate tests run.

- merge-coverage
  This merges all available .exec files (unit + API test data) into one file for reporting.

The custom profiles also keeps the custom build steps clean, and doesn't impact the default mvn clean install.
