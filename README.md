# JUsecase Guice
[![Build Status](https://travis-ci.org/casid/jusecase-guice.svg?branch=master)](https://travis-ci.org/casid/jusecase-guice)
[![Coverage Status](https://coveralls.io/repos/github/casid/jusecase-guice/badge.svg?branch=master)](https://coveralls.io/github/casid/jusecase-guice?branch=master)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://raw.githubusercontent.com/casid/jusecase-guice/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/org.jusecase/jusecase-guice.svg)](http://mvnrepository.com/artifact/org.jusecase/jusecase-guice)

A use case executor for [JUsecase](https://github.com/casid/jusecase) that uses [Guice](https://github.com/google/guice) for dependency injection.

## Setup
JUsecase Guice is available on maven central repository:
```xml
<dependency>
    <groupId>org.jusecase</groupId>
    <artifactId>jusecase-guice</artifactId>
    <version>0.3.0</version>
</dependency>
```

## Sample usage
```java
public class BusinessLogic extends GuiceUsecaseExecutor {
    public BusinessLogic() {
        super(Guice.createInjector());
        
        addUsecase(Login.class);
        addUsecase(Logout.class);
        addUsecase(GetUsers.class);
        // ...
    }
}
```

## Usage in real-life project
Have a look at the [BusinessLogic class](https://github.com/casid/mazebert-ladder/blob/master/src/main/java/com/mazebert/BusinessLogic.java) at the Mazebert TD ladder backend project.
