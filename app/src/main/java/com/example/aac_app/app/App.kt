package com.example.aac_app.app

import android.app.Application
import androidx.room.Room
import com.example.aac_app.data.AppDatabase

class App: Application() {

    companion object{
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            context = applicationContext,
            klass = AppDatabase::class.java,
            name = "person_db"
        ).build()
    }

}