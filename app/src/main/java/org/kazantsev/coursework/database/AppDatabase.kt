package org.kazantsev.coursework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kazantsev.coursework.database.daos.ClientDao
import org.kazantsev.coursework.data.Client
import java.lang.IllegalStateException

private const val DB_NAME = "coursework_database"

@Database(entities = [Client::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // client DAO
    abstract fun clientDao(): ClientDao

    // not a singleton
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun initialize(context: Context) {
            if (INSTANCE == null)
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                    INSTANCE = instance
                }
        }

        fun get(): AppDatabase =
            INSTANCE ?: throw IllegalStateException("AppDatabase must be initialized")
    }
}