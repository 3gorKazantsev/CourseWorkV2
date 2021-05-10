package org.kazantsev.coursework.data

import java.util.*

data class Delivery(
    var supplierId: Int,
    var productId: Int,
    var number: Int,
    var pricePerPiece: Int,
    var date: Date,
    val id: Int = 0
)