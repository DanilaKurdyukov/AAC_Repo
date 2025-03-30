package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.adapter.PersonAdapter
import com.example.aac_app.data.model.Person
import com.google.android.material.button.MaterialButton


class PersonFragment : Fragment() {

    private lateinit var recyclerViewPerson: RecyclerView
    private var persons = arrayListOf<Person>()
    private lateinit var personAdapter: PersonAdapter
    private lateinit var addPersonButton: MaterialButton
    private lateinit var editPersonButton: MaterialButton

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
        getData()
        personAdapter = PersonAdapter(requireContext(), persons)
        recyclerViewPerson.adapter = personAdapter
        addPersonButton.setOnClickListener {
           val navController = findNavController()
            navController.navigate(resId = R.id.addEditPersonFragment)
        }
    }

    private fun getData() =
        persons.add(
            Person(
                firstName = "Курдюков",
                middleName = "Данила",
                lastName = "Денисович",
                age = 22,
                phoneNumber = "89912455826",
                height = 170,
                weight = 70
            )
        )

}