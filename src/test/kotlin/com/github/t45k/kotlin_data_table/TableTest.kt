package com.github.t45k.kotlin_data_table

import kotlin.test.Test
import kotlin.test.assertEquals

class TableTest {
    @Test
    fun tableToRowTest() {
        val actual = tableToRow {
            // @formatter:off
            "Bob"   `|` 27 `|` Gender.MALE
            "Alice" `|` 34 `|` Gender.FEMALE
            "Alex"  `|`  1 `|` Gender.MALE
            // @formatter:on
        }.map { (name: String, age: Int, gender: Gender) -> Person(name, age, gender) }

        val expected = listOf(
            Person("Bob", 27, Gender.MALE),
            Person("Alice", 34, Gender.FEMALE),
            Person("Alex", 1, Gender.MALE),
        )

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

        val expected = listOf(
            Person("Bob", 27, Gender.MALE),
            Person("Alice", 34, Gender.FEMALE),
            Person("Alex", 1, Gender.MALE),
        )

        assertEquals(expected, actual)
    }

    private data class Person(val name: String, val age: Int, val gender: Gender)

    private enum class Gender {
        MALE, FEMALE;
    }
}