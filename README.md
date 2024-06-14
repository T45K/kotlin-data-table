[![](https://jitpack.io/v/T45K/kotlin-data-table.svg)](https://jitpack.io/#T45K/kotlin-data-table)

# Kotlin Data Table

Simple data table DSL for Kotlin.<br>
This is inspired by [Spock Data Tables](https://spockframework.org/spock/docs/2.3/data_driven_testing.html#data-tables).

## Install

Please refer to [JitPack page](https://jitpack.io/#T45K/kotlin-data-table/0.1.1).

### Maven

```pom.xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.t45k</groupId>
  <artifactId>kotlin-data-table</artifactId>
  <version>0.0.1</version>
</dependency>
```

### Gradle

```build.gradle.kts
import java.net.URI

repositories {
    maven { url = URI("https://jitpack.io") }
}

dependencies {
    implementation("com.github.T45K:kotlin-data-table:0.1.0")
}
```

## How to use

Please refer to [test cases](./src/test/kotlin/io/github/t45k/kotlin_data_table/TableTest.kt).

`tableToRow` converts table DSL to tuple of columns.<br>
It is destructive.

```kotlin
tableToRow {
  "Bob"   `|` 27 `|` Gender.MALE
  "Alice" `|` 34 `|` Gender.FEMALE
  "Alex"  `|`  1 `|` Gender.MALE
}.map { (name: String, age: Int, gender: Gender) -> Person(name, age, gender) }

listOf(
  Person("Bob", 27, Gender.MALE),
  Person("Alice", 34, Gender.FEMALE),
  Person("Alex", 1, Gender.MALE),
)
```

`tableToRowWithName` converts table DSL to tuple of columns by name.<br>
It is like `Map` structure, i.e., you can get value by name.

```kotlin
tableToRowWithName {
  "name"  `|` "age" `|` "gender" // column names are necessary in the first row
  "Bob"   `|`    27 `|` Gender.MALE
  "Alice" `|`    34 `|` Gender.FEMALE
  "Alex"  `|`     1 `|` Gender.MALE
}.map { Person(it["name"], it["age"], it["gender"]) } // get value by column name

listOf(
  Person("Bob", 27, Gender.MALE),
  Person("Alice", 34, Gender.FEMALE),
  Person("Alex", 1, Gender.MALE),
)
```

You cal use multi-line string instead.<br>
Please note that it is your own responsibility to map string type to appropriate type.

```kotlin
strTableToRow(
  """
    Bob   |  27 | MALE
    Alice |  34 | FEMALE
    Alex  |   1 | MALE
  """.trimIndent()
).map { (name, age, gender) -> Person(name, age.toInt(), Gender.valueOf(gender)) }

strTableToRowWithName(
  """
    name  | age | gender
    Bob   |  27 | MALE
    Alice |  34 | FEMALE
    Alex  |   1 | MALE
  """.trimIndent()
).map { Person(it["name"], it["age"].toInt(), Gender.valueOf(it["gender"])) }
```
