package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.FragmentAddEditPersonBinding
import com.example.aac_app.presentation.ui.vm.AddEditPersonVM
import com.example.aac_app.presentation.ui.vm.AddEditPersonVMFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class AddEditPersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_add_edit_person,
            container,
            false
        )
        //necessary for binding
        binding.lifecycleOwner = this

        return binding.root
    }

    private val addEditVM: AddEditPersonVM by viewModels {
        AddEditPersonVMFactory(personDAO = personDAO)
    }
    private val personDAO by lazy { App.database.personDao() }

    private lateinit var binding: FragmentAddEditPersonBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addEditVM = addEditVM
    }

    override fun onResume() {
        super.onResume()
        getArgs()
    }

    private fun getArgs() {
        val args = navArgs<AddEditPersonFragmentArgs>()
        val id = args.value.personId
        if(id != null){
            addEditVM.getPersonById(id)
        }
    }

    /*private suspend fun add(){
        val person = Person(
            id = 0,
            firstName = txtFirstName.text.toString(),
            middleName = txtMiddleName.text.toString(),
            lastName = txtLastName.text.toString(),
            age = Integer.valueOf(txtAge.text.toString()),
            phoneNumber = txtPhoneNumber.text.toString(),
            height = Integer.valueOf(txtHeight.text.toString()),
            weight = Integer.valueOf(txtWeight.text.toString()),
        )
        personDAO.add(person)

    }*/

   /* private suspend fun update(){
        current?.firstName = txtFirstName.text.toString()
        current?.middleName = txtMiddleName.text.toString()
        current?.lastName = txtLastName.text.toString()
        current?.age = Integer.valueOf(txtAge.text.toString())
        current?.phoneNumber = txtPhoneNumber.text.toString()
        current?.height = Integer.valueOf(txtHeight.text.toString())
        current?.weight = Integer.valueOf(txtWeight.text.toString())
        personDAO.update(current!!)
    }*/


}