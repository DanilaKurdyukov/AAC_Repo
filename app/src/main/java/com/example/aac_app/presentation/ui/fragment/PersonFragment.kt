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
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.presentation.ui.adapter.PersonAdapter
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.FragmentPersonBinding
import com.example.aac_app.presentation.ui.util.OnItemClickListener
import com.example.aac_app.presentation.ui.vm.GetPersonViewModel
import com.example.aac_app.presentation.ui.vm.PersonViewModelFactory
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch


class PersonFragment : Fragment() {

    private lateinit var recyclerViewPerson: RecyclerView
    private var persons = arrayListOf<Person>()
    private lateinit var personAdapter: PersonAdapter
    private lateinit var addPersonButton: MaterialButton
    private lateinit var editPersonButton: MaterialButton
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


        /*recyclerViewPerson = view.findViewById(R.id.recycler_view_person)
        addPersonButton = view.findViewById(R.id.button_addPerson)
        editPersonButton = view.findViewById(R.id.button_editPerson)*/
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


        personVM.load()
        personVM._persons.observe(this, {
            persons = it
            personAdapter = PersonAdapter(persons)
            binding.recyclerViewPerson.adapter = personAdapter
        })
       /* persons = personDAO.get() as ArrayList<Person>
        personAdapter = PersonAdapter(persons = persons)
        personAdapter.setOnItemClickListener(object: OnItemClickListener{
            override fun onItemClick(person: Person) {

            }
        })
        recyclerViewPerson.adapter = personAdapter*/
    }

}