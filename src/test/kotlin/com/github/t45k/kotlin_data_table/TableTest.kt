package com.github.t45k.kotlin_data_table

import kotlin.test.Test
import kotlin.test.assertEquals

class TableTest {
    private val expected = listOf(
        Person("Bob", 27, Gender.MALE),
        Person("Alice", 34, Gender.FEMALE),
        Person("Alex", 1, Gender.MALE),
    )

    @Test
    fun tableToRowTest() {
        val actual = tableToRow {
            // @formatter:off
            "Bob"   `|` 27 `|` Gender.MALE
            "Alice" `|` 34 `|` Gender.FEMALE
            "Alex"  `|`  1 `|` Gender.MALE
            // @formatter:on
        }.map { (name: String, age: Int, gender: Gender) -> Person(name, age, gender) }

        assertEquals(expected, actual)
    }

    @Test
    fun tableToRowWithNameTest() {
        val actual = tableToRowWithName {
            // @formatter:off
            "name"  `|` "age" `|` "gender"
            "Bob"   `|`    27 `|` Gender.MALE
            "Alice" `|`    34 `|` Gender.FEMALE
            "Alex"  `|`     1 `|` Gender.MALE
            // @formatter:on
        }.map { Person(it["name"], it["age"], it["gender"]) }

        assertEquals(expected, actual)
    }

    @Test
    fun strTableToRow() {
        val actual = strTableToRow(
            """
                Bob   |  27 | MALE
                Alice |  34 | FEMALE
                Alex  |   1 | MALE
            """.trimIndent()
        ).map { (name, age, gender) -> Person(name, age.toInt(), Gender.valueOf(gender)) }

        assertEquals(expected, actual)
    }

    @Test
    fun `strTableToRow ignores empty line`() {
        val actual = strTableToRow(
            """
                Bob   |  27 | MALE
                
                Alice |  34 | FEMALE
                
                Alex  |   1 | MALE
            """
        ).map { (name, age, gender) -> Person(name, age.toInt(), Gender.valueOf(gender)) }

        assertEquals(expected, actual)
    }

    @Test
    fun strTableToRowWithName() {
        val actual = strTableToRowWithName(
            """
                name  | age | gender
                Bob   |  27 | MALE
                Alice |  34 | FEMALE
                Alex  |   1 | MALE
            """.trimIndent()
        ).map { Person(it["name"], it["age"].toInt(), Gender.valueOf(it["gender"])) }

        assertEquals(expected, actual)
    }

    @Test
    fun `strTableToRowWithName ignores empty line`() {
        val actual = strTableToRowWithName(
            """
                name  | age | gender
                
                Bob   |  27 | MALE
                
                Alice |  34 | FEMALE
                
                Alex  |   1 | MALE
            """
        ).map {
            Person(it["name"], it["age"].toInt(), Gender.valueOf(it["gender"]))
        }

        assertEquals(expected, actual)
    }

    private data class Person(val name: String, val age: Int, val gender: Gender)

    private enum class Gender {
        MALE, FEMALE;
    }
}