package org.kazantsev.coursework.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees_table")
data class Employee(
    @ColumnInfo(name = "full_name")
    var fullName: String = "",
    var phone: String = "",
    var post: String = "",
    @PrimaryKey
    val id: Int = 0
)
