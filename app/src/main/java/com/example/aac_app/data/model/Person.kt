package com.example.aac_app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id") val id: Int,
    @ColumnInfo(name="first_name") var firstName: String,
    @ColumnInfo(name="middle_name") var middleName: String,
    @ColumnInfo(name="last_name") var lastName: String,
    @ColumnInfo(name="age") var age: Int,
    @ColumnInfo(name="phone_number") var phoneNumber: String,
    @ColumnInfo(name="height") var height: Int,
    @ColumnInfo(name="weight") var weight: Int
)