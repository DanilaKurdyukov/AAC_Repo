package com.example.aac_app.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aac_app.data.model.Person

@Dao
interface PersonDAO {

    @Query("SELECT * FROM person")
    suspend fun get(): List<Person>

    @Insert
    suspend fun add(vararg person: Person)

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)

}