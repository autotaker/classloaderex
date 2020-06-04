# ClassLoaderEx

This repository describes how to get resource files in java.

See [AppTest.java](main/src/test/java/org/example/AppTest.java) for specification.

## Directory Structure

```
.
├── README.md
├── core
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── org
│       │   │       └── example
│       │   │           └── core
│       │   │               └── Core.java
│       │   └── resources
│       │       └── org
│       │           └── example
│       │               └── core
│       │                   └── hello.txt
│       └── test
│           └── resources
│               └── org
│                   └── example
│                       └── core
│                           └── coretest.txt
├── main
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── org
│       │   │       └── example
│       │   │           └── App.java
│       │   └── resources
│       │       ├── main.txt
│       │       └── org
│       │           └── example
│       │               └── main.txt
│       └── test
│           ├── java
│           │   └── org
│           │       └── example
│           │           └── AppTest.java
│           └── resources
│               └── org
│                   └── example
│                       └── maintest.txt
└── pom.xml
```

## How to run test
```bash
mvn package
java -cp main/target/main-1.0-SNAPSHOT.jar:core/target/core-1.0-SNAPSHOT.jar org.example.App
```

Expected Result

```
hello.txt: jar:file:/Users/autotaker/Programs/ClassLoader/classloaderex/core/target/core-1.0-SNAPSHOT.jar!/org/example/core/hello.txt
main.txt: jar:file:/Users/autotaker/Programs/ClassLoader/classloaderex/main/target/main-1.0-SNAPSHOT.jar!/org/example/main.txt
maintest.txt: null
```
