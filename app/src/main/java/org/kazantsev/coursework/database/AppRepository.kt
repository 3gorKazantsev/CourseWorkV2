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

    // get all
    fun getAllClients() = clientDao.getAllClients()

    // get one
    fun getClient(id: Int) = clientDao.getClient(id)

    // delete one
    suspend fun deleteClient(client: Client) = clientDao.deleteClient(client)
}