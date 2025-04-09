package com.example.aac_app.presentation.ui.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.model.Person
import com.example.aac_app.databinding.PersonItemBinding
import com.example.aac_app.presentation.ui.util.OnItemClickListener
import androidx.core.util.size
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class PersonAdapter(): ListAdapter<Person, PersonAdapter.ViewHolder>(PersonDiffCallback()) {

    private lateinit var mListener: OnItemClickListener
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

    fun setOnItemClickListener(mListener: OnItemClickListener){
        this.mListener = mListener
    }

   inner class ViewHolder(val binding: PersonItemBinding) : RecyclerView.ViewHolder(binding.root){
       init {
           //binding.personItemClickListener = mListener
       }
   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.person = getItem(position)
        //for selecting
        holder.binding.root.isSelected = selectedItems.get(position, false)

    }

    class PersonDiffCallback: DiffUtil.ItemCallback<Person>(){
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

    fun updateSelectedItemsAfterDeletion(deletedPosition: Int) {
        if (selectedItems.get(deletedPosition, false)) {
            selectedItems.delete(deletedPosition)
        }

        val updatedSelectedItems = SparseBooleanArray()
        for (i in 0 until selectedItems.size) {
            val key = selectedItems.keyAt(i)
            val value = selectedItems.valueAt(i)
            if (key > deletedPosition) {
                updatedSelectedItems.put(key - 1, value)
            } else if (key < deletedPosition) {
                updatedSelectedItems.put(key, value)
            }
        }
        selectedItems.clear()
        for (i in 0 until updatedSelectedItems.size) {
            selectedItems.put(updatedSelectedItems.keyAt(i), updatedSelectedItems.valueAt(i))
        }
    }
}