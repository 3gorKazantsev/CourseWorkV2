package org.kazantsev.coursework.data

data class Supplier(
    var name: String,
    val inn: String,
    var phone: String,
    var email: String,
    val id: Int = 0
)