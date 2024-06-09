package com.github.t45k.kotlin_data_table

fun tableToRowWithName(dsl: TableToRowWithNameDSL.() -> Unit): List<TableRowWithName> {
    val dslBody = TableToRowWithNameDSL()
    dslBody.dsl()
    return (dslBody.rows + dslBody.lastRow!!)
        .map { row -> dslBody.header.zip(row.values).toMap() }
        .map(::TableRowWithName)
}

class TableToRowWithNameDSL {
    internal lateinit var header: List<String>
    internal var lastRow: TableRow? = null
    internal val rows: MutableList<TableRow> = mutableListOf()

    infix fun Any.`|`(other: Any): TableRow {
        if (lastRow != null) {
            if (::header.isInitialized) {
                rows += lastRow!!
            } else {
                header = lastRow!!.values.map { it as String }
            }
        }
        lastRow = TableRow(mutableListOf(this, other))
        return lastRow!!
    }
}

class TableRowWithName(private val values: Map<String, Any>) {
    operator fun <T> get(key: String): T = values[key] as T

    override fun toString(): String = values.toString()
    override fun hashCode(): Int = values.hashCode()
    override fun equals(other: Any?): Boolean =
        this.values == (other as? TableRow)?.values
}
