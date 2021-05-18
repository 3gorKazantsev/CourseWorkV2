package org.kazantsev.coursework.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.database.AppRepository

class ClientViewModel : ViewModel() {

    // repository
    private val repository = AppRepository()

    // client id
    private val clientId = MutableLiveData<Int>()

    // client
    val client: LiveData<Client?> =
        Transformations.switchMap(clientId) { id ->
            repository.getClient(id)
        }

    fun loadClient(id: Int) {
        clientId.value = id
    }

    fun updateClient(client: Client) {
        viewModelScope.launch {
            repository.updateClient(client)
        }
    }

    fun insertClient(client: Client) {
        viewModelScope.launch {
            repository.insertClient(client)
        }
    }

    fun deleteClient(client: Client) {
        viewModelScope.launch {
            repository.deleteClient(client)
        }
    }
}