package com.example.aac_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id") val id: Int,
    @ColumnInfo(name="first_name") val firstName: String,
    @ColumnInfo(name="middle_name") val middleName: String,
    @ColumnInfo(name="last_name") val lastName: String,
    @ColumnInfo(name="age") val age: Int,
    @ColumnInfo(name="phone_number") val phoneNumber: String,
    @ColumnInfo(name="height") val height: Int,
    @ColumnInfo(name="weight") val weight: Int
)