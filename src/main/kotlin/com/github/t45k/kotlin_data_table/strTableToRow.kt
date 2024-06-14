package com.github.t45k.kotlin_data_table

fun strTableToRow(table: String, delimiter: String = "|"): List<StrTableRow> {
    return table.split(System.lineSeparator())
        .filter { it.isNotBlank() }
        .map { line -> line.split(delimiter).map { it.trim() } }
        .map { StrTableRow(it) }
}

class StrTableRow(private val values: List<String>) {
    operator fun component1(): String = values[0]
    operator fun component2(): String = values[1]
    operator fun component3(): String = values[2]
    operator fun component4(): String = values[3]
    operator fun component5(): String = values[4]
    operator fun component6(): String = values[5]
    operator fun component7(): String = values[6]
    operator fun component8(): String = values[7]
    operator fun component9(): String = values[8]
    operator fun component10(): String = values[9]

    operator fun get(index: Int): String = values[index]

    override fun toString(): String = values.toString()
    override fun hashCode(): Int = values.hashCode()
    override fun equals(other: Any?): Boolean =
        this.values == (other as? TableRow)?.values
}
