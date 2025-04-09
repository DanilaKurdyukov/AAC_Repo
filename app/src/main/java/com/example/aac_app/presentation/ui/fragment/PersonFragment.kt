package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.app.App
import com.example.aac_app.presentation.ui.adapter.PersonAdapter
import com.example.aac_app.data.model.Person
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewPerson = view.findViewById(R.id.recycler_view_person)
        addPersonButton = view.findViewById(R.id.button_addPerson)
        editPersonButton = view.findViewById(R.id.button_editPerson)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            getData()
        }
        addPersonButton.setOnClickListener {
           val navController = findNavController()
            navController.navigate(resId = R.id.addEditPersonFragment)
        }
        editPersonButton.setOnClickListener {
            findNavController().navigate(PersonFragmentDirections.actionPersonFragmentToAddEditPersonFragment(selectedPerson!!.id))
        }

    }



    private suspend fun getData() {
        persons = personDAO.get() as ArrayList<Person>
        personAdapter = PersonAdapter(context = requireContext(), persons = persons)
        personAdapter.setOnItemClickListener(object: PersonAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                selectedPerson = persons[position]
            }
        })
        recyclerViewPerson.adapter = personAdapter
    }

}