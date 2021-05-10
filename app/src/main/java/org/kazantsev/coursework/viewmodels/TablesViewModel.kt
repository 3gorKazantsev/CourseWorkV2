package org.kazantsev.coursework.viewmodels

import androidx.lifecycle.ViewModel
import org.kazantsev.coursework.data.Table

class TablesViewModel : ViewModel() {
    fun getList(): List<Table> {
        return Table.values().toList()
    }
}