package com.example.aac_app.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.data.model.Person
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private val personDAO by lazy { App.database.personDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        navController.navigate(resId = R.id.personFragment)
        NavigationUI.setupWithNavController(toolbar, navController)

        lifecycleScope.launch {
            //addData()
        }
        }

    private suspend fun addData(){
        val person = Person(
            id = 0,
            firstName = "Курдюков",
            middleName = "Данила",
            lastName = "Денисович",
            age = 22,
            phoneNumber = "89912455826",
            height = 170,
            weight = 70
        )
        val person1 = Person(
            id = 0,
            firstName = "Шолохов",
            middleName = "Максим",
            lastName = "Дмитриевич",
            age = 22,
            phoneNumber = "89912455826",
            height = 170,
            weight = 70
        )
        personDAO.add(person, person1)

    }

    }

