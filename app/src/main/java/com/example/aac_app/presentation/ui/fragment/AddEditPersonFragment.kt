package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.data.model.Person
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class AddEditPersonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit_person, container, false)
    }

    private val personDAO by lazy { App.database.personDao() }
    private lateinit var txtFirstName: TextInputEditText
    private lateinit var txtMiddleName: TextInputEditText
    private lateinit var txtLastName: TextInputEditText
    private lateinit var txtAge: TextInputEditText
    private lateinit var txtPhoneNumber: TextInputEditText
    private lateinit var txtHeight: TextInputEditText
    private lateinit var txtWeight: TextInputEditText
    private lateinit var btnSave: MaterialButton

    private var current: Person? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()

        getArgs()

        btnSave.setOnClickListener{
            lifecycleScope.launch {
                if(current!=null){
                    update()
                } else {
                    add()
                }
                findNavController().navigate(R.id.personFragment)
            }

        }
    }

    private fun getArgs() {
        val args = navArgs<AddEditPersonFragmentArgs>()
        if(args.value.personId > 0){
            lifecycleScope.launch {
                current = personDAO.getPersonById(args.value.personId)
                txtFirstName.setText(current?.firstName)
                txtMiddleName.setText(current?.middleName)
                txtLastName.setText(current?.lastName)
                txtAge.setText(current?.age.toString())
                txtPhoneNumber.setText(current?.phoneNumber)
                txtHeight.setText(current?.height.toString())
                txtWeight.setText(current?.weight.toString())
            }
        }
    }

    private suspend fun add(){
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

    }

    private suspend fun update(){
        current?.firstName = txtFirstName.text.toString()
        current?.middleName = txtMiddleName.text.toString()
        current?.lastName = txtLastName.text.toString()
        current?.age = Integer.valueOf(txtAge.text.toString())
        current?.phoneNumber = txtPhoneNumber.text.toString()
        current?.height = Integer.valueOf(txtHeight.text.toString())
        current?.weight = Integer.valueOf(txtWeight.text.toString())
        personDAO.update(current!!)
    }

    private fun init(){
        txtFirstName = requireView().findViewById(R.id.edit_text_firstName)
        txtMiddleName = requireView().findViewById(R.id.edit_text_middleName)
        txtLastName = requireView().findViewById(R.id.edit_text_lastName)
        txtAge = requireView().findViewById(R.id.edit_text_age)
        txtPhoneNumber = requireView().findViewById(R.id.edit_text_phoneNumber)
        txtHeight = requireView().findViewById(R.id.edit_text_height)
        txtWeight = requireView().findViewById(R.id.edit_text_weight)
        btnSave = requireView().findViewById(R.id.button_savePerson)
    }

}