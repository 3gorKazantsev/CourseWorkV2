package org.kazantsev.coursework.data

data class Product(
    var name: String,
    var desc: String,
    var price: Int,
    var rest: Int,
    var discount: Int,
    var inStock: Boolean,
    val id: Int = 0
)
