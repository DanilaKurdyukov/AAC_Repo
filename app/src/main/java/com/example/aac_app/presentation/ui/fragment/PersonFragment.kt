package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.presentation.ui.adapter.PersonAdapter
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.FragmentPersonBinding
import com.example.aac_app.presentation.ui.vm.GetPersonViewModel
import com.example.aac_app.presentation.ui.vm.PersonViewModelFactory

class PersonFragment : Fragment() {


    private var persons = arrayListOf<Person>()
    private lateinit var personAdapter: PersonAdapter
    private var selectedPerson: Person? = null
    private val personDAO by lazy { App.database.personDao() }

    private lateinit var binding: FragmentPersonBinding
    private val personVM: GetPersonViewModel by viewModels {
        PersonViewModelFactory(personDAO)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        getData()
        /*addPersonButton.setOnClickListener {
           val navController = findNavController()
            navController.navigate(resId = R.id.addEditPersonFragment)
        }
        editPersonButton.setOnClickListener {
            findNavController().navigate(PersonFragmentDirections.actionPersonFragmentToAddEditPersonFragment(selectedPerson!!.id))
        }*/

    }



    private fun getData() {

        personAdapter = PersonAdapter(object: PersonAdapter.OnItemClickListener{
            override fun onItemClick(person: Person) {
                
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