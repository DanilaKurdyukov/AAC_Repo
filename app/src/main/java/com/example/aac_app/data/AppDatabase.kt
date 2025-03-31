package com.example.aac_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aac_app.data.dao.PersonDAO
import com.example.aac_app.data.model.Person

@Database(
    entities = [
        Person::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personDao() : PersonDAO

}