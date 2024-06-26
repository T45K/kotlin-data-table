package com.github.t45k.kotlin_data_table

import kotlin.test.Test
import kotlin.test.assertEquals

class TableTest {
    private val expected = listOf(
        Person("Bob", 27, Gender.MALE, "foo"),
        Person("Alice", 34, Gender.FEMALE, "bar"),
        Person("Alex", 1, Gender.MALE, "baz"),
    )

    @Test
    fun tableToRowTest() {
        val actual = tableToRow {
            // @formatter:off
            "Bob"   `|` 27 `|` Gender.MALE   `|` "foo"
            "Alice" `|` 34 `|` Gender.FEMALE `|` "bar"
            "Alex"  `|`  1 `|` Gender.MALE   `|` "baz"
            // @formatter:on
        }.map { (name: String, age: Int, gender: Gender, remark: String) -> Person(name, age, gender, remark) }

        assertEquals(expected, actual)
    }

    @Test
    fun tableToRowWithNameTest() {
        val actual = tableToRowWithName {
            // @formatter:off
            "name"  `|` "age" `|` "gender"       `|` "remark"
            "Bob"   `|`    27 `|` Gender.MALE    `|` "foo"
            "Alice" `|`    34 `|` Gender.FEMALE  `|` "bar"
            "Alex"  `|`     1 `|` Gender.MALE    `|` "baz"
            // @formatter:on
        }.map { Person(it["name"], it["age"], it["gender"], it["remark"]) }

        assertEquals(expected, actual)
    }

    @Test
    fun strTableToRow() {
        val actual = strTableToRow(
            """
                Bob   |  27 | MALE   | foo
                Alice |  34 | FEMALE | bar
                Alex  |   1 | MALE   | baz
            """.trimIndent()
        ).map { (name, age, gender, remark) -> Person(name, age.toInt(), Gender.valueOf(gender), remark) }

        assertEquals(expected, actual)
    }

    @Test
    fun `strTableToRow ignores empty line`() {
        val actual = strTableToRow(
            """
                Bob   |  27 | MALE   | foo
                
                Alice |  34 | FEMALE | bar
                
                Alex  |   1 | MALE   | baz
            """
        ).map { (name, age, gender, remark) -> Person(name, age.toInt(), Gender.valueOf(gender), remark) }

        assertEquals(expected, actual)
    }

    @Test
    fun strTableToRowWithName() {
        val actual = strTableToRowWithName(
            """
                name  | age | gender | remark
                Bob   |  27 | MALE   | foo
                Alice |  34 | FEMALE | bar
                Alex  |   1 | MALE   | baz
            """.trimIndent()
        ).map { Person(it["name"], it["age"].toInt(), Gender.valueOf(it["gender"]), it["remark"]) }

        assertEquals(expected, actual)
    }

    @Test
    fun `strTableToRowWithName ignores empty line`() {
        val actual = strTableToRowWithName(
            """
                name  | age | gender | remark
                
                Bob   |  27 | MALE   | foo
                
                Alice |  34 | FEMALE | bar
                
                Alex  |   1 | MALE   | baz
            """
        ).map {
            Person(it["name"], it["age"].toInt(), Gender.valueOf(it["gender"]), it["remark"])
        }

        assertEquals(expected, actual)
    }

    private data class Person(val name: String, val age: Int, val gender: Gender, val remark: String)

    private enum class Gender {
        MALE, FEMALE;
    }
}