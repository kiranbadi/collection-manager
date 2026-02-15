
# Collection Manager

Collection Manager is a Maven-based Java application built with Java 25 that allows users to manage recipes and ingredients through a console-driven interface. The project demonstrates object-oriented design, robust input validation, unit testing, API documentation, and code coverage reporting using industry-standard tools.

---

## Features

- Create and manage recipes and ingredients
- Strong input validation for strings, integers, floats, and doubles
- Console-based menu-driven interface
- Comprehensive unit testing with JUnit 5
- Code coverage reporting with JaCoCo
- API and Test API documentation using Javadoc

---

## Technology Stack

- **Language:** Java 25  
- **Build Tool:** Apache Maven  
- **Testing Framework:** JUnit 5  
- **Code Coverage:** JaCoCo  
- **Documentation:** Javadoc  

---

## Project Structure

```text
collection-manager/
├── src/
│   ├── main/java/edu/snhu/
│   │   ├── CollectionManager.java
│   │   ├── RecipeBox.java
│   │   ├── Recipe.java
│   │   ├── Ingredient.java
│   │   └── RecipeHelper.java
│   └── test/java/edu/snhu/
│       └── (JUnit test classes)
├── pom.xml
└── README.md
````

---

## Prerequisites

Before building or running the project, ensure the following is installed:

* **JDK 25**
* **Apache Maven 3.9+**

Verify installations:

```bash
java -version
mvn -version
```

Ensure Maven is using **Java 25**.

---

## Maven Setup

### macOS

```bash
brew install maven
```

If needed, set Java 25 explicitly:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)
export PATH="$JAVA_HOME/bin:$PATH"
```

### Windows

1. Install **JDK 25**
2. Set environment variables:

    * `JAVA_HOME` → JDK installation directory
    * Add `%JAVA_HOME%\bin` to `Path`
3. Install Maven:

```bat
choco install maven
```

Verify:

```bat
mvn -version
```

---

## Running the Project Using Maven

This is a **plain Java console application**, not Spring Boot.

### Step 1: Compile the project

From the project root:

```bash
mvn clean compile
```

This downloads dependencies and compiles the source code.

---

### Step 2: Run the application

#### Option A: Run using Maven Exec Plugin (recommended)

If your `pom.xml` includes the Maven Exec Plugin:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.5.0</version>
    <configuration>
        <mainClass>edu.snhu.CollectionManager</mainClass>
    </configuration>
</plugin>
```

Run:

```bash
mvn clean compile exec:java
```

This launches the RecipeBox menu in the console.

---

#### Option B: Run the packaged JAR

```bash
mvn clean package
java -jar target/collection-manager-1.0-SNAPSHOT.jar
```

---

## Build and Test

Run unit tests:

```bash
mvn test
```

Run tests with coverage:

```bash
mvn clean verify
```

---

## Generating Coverage and Documentation

Run the following command to generate **tests, coverage, API docs, and test API docs**:

```bash
mvn clean verify javadoc:javadoc javadoc:test-javadoc
```

### Generated Outputs

| Artifact               | Location                                |
|------------------------|-----------------------------------------|
| JaCoCo Coverage Report | `target/site/jacoco/index.html`         |
| Main API Documentation | `target/reports/apidocs/index.html`     |
| Test API Documentation | `target/reports/testapidocs/index.html` |

---

## Accessing API Documentation (For Developers)

### Main API Documentation

The main API documentation describes:

* Core classes (`Recipe`, `Ingredient`, `RecipeBox`, etc.)
* Public methods and expected behavior
* Parameters, return values, and usage notes

**Location:**

```text
target/reports/apidocs/index.html
```

Open the file in any web browser and navigate by package or class name.

---

## Accessing Test API Documentation

Test API documentation provides insight into:

* JUnit test classes
* Test intent and validation paths
* Edge cases and error handling

**Location:**

```text
target/reports/testapidocs/index.html
```

This is useful for developers extending or maintaining the test suite.

---

## Accessing Code Coverage Reports

Code coverage is generated using **JaCoCo** and includes:

* Line coverage
* Branch coverage
* Visual indicators for untested code

**Location:**

```text
target/site/jacoco/index.html
```

### How to interpret the report

* **Green:** Code executed by tests
* **Yellow:** Partial branch coverage
* **Red:** Code not covered by tests

Coverage reports help engineers identify gaps and improve test quality.

---

## Viewing Reports Locally

### macOS

```bash
open target/reports/apidocs/index.html
open target/reports/testapidocs/index.html
open target/site/jacoco/index.html
```

### Windows

```bat
start target\reports\apidocs\index.html
start target\reports\testapidocs\index.html
start target\site\jacoco\index.html
```

All reports are static HTML files and require only a web browser.

---

## Typical Developer Workflow

1. Clone the repository
2. Run:

   ```bash
   mvn clean verify javadoc:javadoc javadoc:test-javadoc
   ```
3. Review:

    * API documentation
    * Test documentation
    * Coverage reports
4. Make changes or add features
5. Add/update tests and re-run coverage

---

## Sharing Documentation with the Team

Generated documentation can be:

* Opened locally
* Zipped and shared

No additional tooling is required beyond a web browser.

---

## Design Notes

* `RecipeHelper` is a **utility class** with a private constructor
* Input validation is centralized and reusable
* Scanner-based input is tested using simulated input streams
* The design emphasizes clarity, maintainability, and testability

---

## Author

**Kiran Badi**


---

## License

This project is intended for educational and coursework purposes.

```

