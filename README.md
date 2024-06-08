# Kotlin Data Table

Simple data table DSL for Kotlin.

This is expired by [Spock Data Tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables).

## Install 

### Maven

```pom.xml
<dependency>
  <groupId>io.github.t45k</groupId>
  <artifactId>kotlin-data-table</artifactId>
  <version>0.0.1</version>
</dependency>
```

### Gradle

```build.gradle.kts
repositories {
    maven {
        url = URI("https://maven.pkg.github.com/T45K/kotlin-data-table")
    }
}

dependencies {
    implementation("io.github.t45k:kotlin-data-table:0.0.1")
}
```

## How to use

Please refer to [test cases](./src/test/kotlin/io/github/t45k/kotlin_data_table/TableTest.kt).
