package org.kazantsev.coursework.data

data class Employee(
    var fullName: String,
    var phone: String,
    var post: String,
    val id: Int = 0
)
