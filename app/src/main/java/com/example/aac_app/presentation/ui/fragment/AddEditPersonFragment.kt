package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
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
    private var edit = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addEditVM = addEditVM
    }

    override fun onResume() {
        super.onResume()
        getArgs()
        val onSaveClickListener = View.OnClickListener {
            save()
        }
        binding.onSaveClickListener = onSaveClickListener
    }

    private fun getArgs() {
        val args = navArgs<AddEditPersonFragmentArgs>()
        val id = args.value.personId
        if(id!=-1){
            edit = true
            addEditVM.getPersonById(id)
        }
    }

    private fun save(){
        if(!edit){
            val person = Person(
                id = 0,
                firstName = binding.editTextFirstName.text.toString(),
                lastName = binding.editTextLastName.text.toString(),
                middleName = binding.editTextMiddleName.text.toString(),
                age = Integer.valueOf(binding.editTextAge.text.toString()),
                phoneNumber = binding.editTextPhoneNumber.text.toString(),
                height = Integer.valueOf(binding.editTextHeight.text.toString()),
                weight = Integer.valueOf(binding.edtiTextWeight.text.toString())
            )
            addEditVM.add(person)
        } else {
            addEditVM.update()
        }
        findNavController().navigate(R.id.personFragment)
    }

}