package com.github.t45k.kotlin_data_table

fun tableToRow(dsl: TableToRowDSL.() -> Unit): List<TableRow> {
    val dslBody = TableToRowDSL()
    dslBody.dsl()
    return dslBody.rows + dslBody.lastRow!!
}

class TableToRowDSL {
    internal var lastRow: TableRow? = null
    internal val rows: MutableList<TableRow> = mutableListOf()

    infix fun Any.`|`(other: Any): TableRow {
        if (lastRow != null) {
            rows += lastRow!!
        }
        lastRow = TableRow(mutableListOf(this, other))
        return lastRow!!
    }
}

class TableRow(internal val values: MutableList<Any>) {
    infix fun `|`(other: Any): TableRow {
        values += other
        return this
    }

    operator fun <T> component1(): T = values[0] as T
    operator fun <T> component2(): T = values[1] as T
    operator fun <T> component3(): T = values[2] as T
    operator fun <T> component4(): T = values[3] as T
    operator fun <T> component5(): T = values[4] as T
    operator fun <T> component6(): T = values[5] as T
    operator fun <T> component7(): T = values[6] as T
    operator fun <T> component8(): T = values[7] as T
    operator fun <T> component9(): T = values[8] as T
    operator fun <T> component10(): T = values[9] as T

    operator fun <T> get(index: Int): T = values[index] as T

    override fun toString(): String = values.toString()
    override fun hashCode(): Int = values.hashCode()
    override fun equals(other: Any?): Boolean =
        this.values == (other as? TableRow)?.values
}
