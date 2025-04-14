package com.example.aac_app.presentation.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_app.data.dao.PersonDAO
import com.example.aac_app.data.model.Person
import kotlinx.coroutines.launch

class GetPersonVM(private val personDAO: PersonDAO): ViewModel() {

    private val persons = MutableLiveData<ArrayList<Person>>()
    val _persons: LiveData<ArrayList<Person>> = persons

    fun load(){
        viewModelScope.launch {
            persons.value = personDAO.get() as ArrayList<Person>
        }
    }

    fun delete(person: Person){
        viewModelScope.launch {
            personDAO.delete(person)
            load()
        }
    }
}