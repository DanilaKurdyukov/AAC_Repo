package com.example.aac_app.presentation.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_app.data.dao.PersonDAO
import com.example.aac_app.data.model.Person
import kotlinx.coroutines.launch

class GetPersonViewModel(val personDAO: PersonDAO): ViewModel() {

    private val persons = MutableLiveData<List<Person>>()

    fun getData(): LiveData<List<Person>>{
        return persons
    }

    fun load(){
        viewModelScope.launch {
            val _persons = personDAO.get()
            persons.value = _persons
        }

    }
}