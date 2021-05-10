package org.kazantsev.coursework.data

data class Client(
    var name: String,
    var addr: String,
    val inn: String,
    var phone: String,
    var email: String,
    val id: Int = 0
)
