package org.kazantsev.coursework.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.database.AppRepository

class TableViewModel : ViewModel() {
    // repository
    private val repository: AppRepository = AppRepository()

    // all clients for RecyclerView
    val allClients: LiveData<List<Client>> = repository.getAllClients()
}