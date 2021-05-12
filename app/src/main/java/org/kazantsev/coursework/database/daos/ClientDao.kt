package org.kazantsev.coursework.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import org.kazantsev.coursework.data.Client

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: Client)

    @Update
    suspend fun updateClient(client: Client)

    @Query("SELECT * FROM clients_table")
    fun getAllClients(): LiveData<List<Client>>

    @Delete
    suspend fun deleteClient(client: Client)
}