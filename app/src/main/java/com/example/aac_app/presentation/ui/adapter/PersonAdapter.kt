package com.example.aac_app.presentation.ui.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.PersonItemBinding
import com.example.aac_app.presentation.ui.util.ItemClickListener
import com.google.android.material.textview.MaterialTextView

class PersonAdapter(private var persons: ArrayList<Person>): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private lateinit var mListener: ItemClickListener
    private val selectedItems = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.person_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = persons.size

   inner class ViewHolder(val binding: PersonItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.person = persons[position]
        //for selecting
        holder.itemView.isSelected = selectedItems.get(position, false)

    }

    fun updateSelectedItemsAfterDeletion(deletedPosition: Int) {
        if (selectedItems.get(deletedPosition, false)) {
            selectedItems.delete(deletedPosition)
        }

        val updatedSelectedItems = SparseBooleanArray()
        for (i in 0 until selectedItems.size()) {
            val key = selectedItems.keyAt(i)
            val value = selectedItems.valueAt(i)
            if (key > deletedPosition) {
                updatedSelectedItems.put(key - 1, value)
            } else if (key < deletedPosition) {
                updatedSelectedItems.put(key, value)
            }
        }
        selectedItems.clear()
        for (i in 0 until updatedSelectedItems.size()) {
            selectedItems.put(updatedSelectedItems.keyAt(i), updatedSelectedItems.valueAt(i))
        }
    }
}