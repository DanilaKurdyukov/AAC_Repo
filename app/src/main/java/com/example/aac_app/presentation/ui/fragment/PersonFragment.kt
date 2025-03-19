package com.example.aac_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.adapter.PersonAdapter
import com.example.aac_app.data.model.Person


class PersonFragment : Fragment() {

    private lateinit var recyclerViewPerson: RecyclerView
    private var persons = arrayListOf<Person>()
    private lateinit var personAdapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewPerson = view.findViewById(R.id.recycler_view_person)
        getData()
        personAdapter = PersonAdapter(requireContext(), persons)
        recyclerViewPerson.adapter = personAdapter
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