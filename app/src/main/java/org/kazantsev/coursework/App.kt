package org.kazantsev.coursework

import android.app.Application
import org.kazantsev.coursework.database.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.initialize(this)
    }
}