package org.kazantsev.coursework.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients_table")
data class Client(
    var name: String,
    var addr: String,
    var inn: String,
    var phone: String,
    var email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
