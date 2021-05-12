package org.kazantsev.coursework.database

import org.kazantsev.coursework.database.daos.ClientDao
import org.kazantsev.coursework.data.Client

class AppRepository() {
    // Client DAO
    private val clientDao: ClientDao = AppDatabase.get().clientDao()

    // insert
    suspend fun insertClient(client: Client) = clientDao.insertClient(client)

    // update
    suspend fun updateClient(client: Client) = clientDao.updateClient(client)

    // get all clients
    fun getAllClients() = clientDao.getAllClients()

    suspend fun deleteClient(client: Client) = clientDao.deleteClient(client)
}