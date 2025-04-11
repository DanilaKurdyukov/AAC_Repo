package com.example.aac_app.presentation.ui.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.aac_app.data.model.Person
import com.example.aac_app.presentation.ui.adapter.PersonAdapter

@BindingAdapter("onPersonClick", "personData", requireAll = true)
fun setOnPersonClick(view: View, listener: PersonAdapter.OnItemClickListener, person: Person) {
    view.setOnClickListener {
        listener.onItemClick(person)
    }
}