# ClassLoaderEx

This repository describes how to get resource files in java.

See [AppTest.java](main/src/test/java/org/example/AppTest.java) for specification.

## Directory Structure

```
.
├── README.md
├── core
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── org
│           │       └── example
│           │           └── core
│           │               └── Core.java
│           └── resources
│               └── org
│                   └── example
│                       └── core
│                           └── hello.txt
├── main
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── org
│       │   │       └── example
│       │   │           └── App.java
│       │   └── resources
│       │       ├── main.txt
│       │       └── org
│       │           └── example
│       │               └── main.txt
│       └── test
│           └── java
│               └── org
│                   └── example
│                       └── AppTest.java
└── pom.xml
```

## How to run test
```bash
mvn package
```
