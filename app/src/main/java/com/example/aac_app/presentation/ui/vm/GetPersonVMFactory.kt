package com.example.aac_app.presentation.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aac_app.data.dao.PersonDAO

class GetPersonVMFactory(private val personDAO: PersonDAO): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetPersonVM(personDAO = personDAO) as T
    }

}