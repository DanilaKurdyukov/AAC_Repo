package com.example.aac_app.presentation.ui.util

import com.example.aac_app.data.model.Person

interface ItemClickListener {
    fun onItemClick(person: Person)
}