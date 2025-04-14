package com.example.aac_app.presentation.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aac_app.data.dao.PersonDAO
import com.example.aac_app.data.model.Person
import kotlinx.coroutines.launch

class AddEditPersonVM(private val personDAO: PersonDAO): ViewModel() {

    var currentPerson = MutableLiveData<Person>()

    fun getPersonById(id: Int){
        viewModelScope.launch {
            currentPerson.value = personDAO.getPersonById(id)
        }
    }

    fun update() {
        viewModelScope.launch {
            personDAO.update(currentPerson.value!!)
        }
    }

    fun add(person: Person) {
        viewModelScope.launch {
            personDAO.add(person)
        }
    }

}