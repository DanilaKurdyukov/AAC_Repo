package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.presentation.ui.adapter.PersonAdapter
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.FragmentPersonBinding
import com.example.aac_app.presentation.ui.vm.GetPersonVM
import com.example.aac_app.presentation.ui.vm.GetPersonVMFactory

class PersonFragment : Fragment() {

    private lateinit var personAdapter: PersonAdapter
    private var selectedPerson: Person? = null
    private val personDAO by lazy { App.database.personDao() }
    private lateinit var binding: FragmentPersonBinding
    private val personVM: GetPersonVM by viewModels {
        GetPersonVMFactory(personDAO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentPersonBinding>(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_person,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getData()
        binding.buttonAddPerson.setOnClickListener {
            findNavController().navigate(resId = R.id.addEditPersonFragment)
        }
        binding.buttonEditPerson.setOnClickListener {
            findNavController().navigate(PersonFragmentDirections.actionPersonFragmentToAddEditPersonFragment(selectedPerson!!.id))
        }
        val onDeleteClickListener = View.OnClickListener {
            delete()
        }
        binding.onDeleteClickListener = onDeleteClickListener
    }

    fun delete(){
        personVM.delete(selectedPerson!!)
    }

    private fun getData() {
        personAdapter = PersonAdapter(object: PersonAdapter.OnItemClickListener{
            override fun onItemClick(person: Person) {
                selectedPerson = person
            }
        })
        binding.recyclerViewPerson.adapter = personAdapter
        personVM._persons.observe(this) {
            it.let {
                personAdapter.submitList(it)
            }
        }
        personVM.load()
    }

}