package com.github.t45k.kotlin_data_table

fun strTableToRowWithName(table: String, delimiter: String = "|"): List<StrTableRowWithName> {
    val lines = table.split(System.lineSeparator())
    val headerColumns = lines[0].split(delimiter).map { it.trim() }
    return lines.subList(1, lines.size)
        .map { line -> line.split(delimiter).map { it.trim() } }
        .map { headerColumns.zip(it).toMap() }
        .map { StrTableRowWithName(it) }
}

class StrTableRowWithName(private val values: Map<String, String>) {
    operator fun get(key: String): String = values[key]!!

    override fun toString(): String = values.toString()
    override fun hashCode(): Int = values.hashCode()
    override fun equals(other: Any?): Boolean =
        this.values == (other as? TableRow)?.values
}
