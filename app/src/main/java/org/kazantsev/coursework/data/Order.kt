package org.kazantsev.coursework.data

data class Order(
    var clientId: Int,
    var productId: Int,
    var employeeId: Int,
    var number: Int,
    var price: Int,
    var state: String,
    val id: Int = 0
)
