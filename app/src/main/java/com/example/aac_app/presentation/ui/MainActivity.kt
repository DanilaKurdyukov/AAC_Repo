package com.example.aac_app.presentation.ui

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.aac_app.R
import com.example.aac_app.presentation.ui.fragment.PersonFragment
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().add(R.id.main_frame, PersonFragment::class.java, null).commit()
    }
}