package com.example.aac_app.presentation.ui.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.model.Person
import com.google.android.material.textview.MaterialTextView

class PersonAdapter(val context: Context, var persons: ArrayList<Person>): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private val selectedItems = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.person_item, parent, false), mListener)
    }

    override fun getItemCount(): Int = persons.size


    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(mListener: onItemClickListener){
        this.mListener = mListener
    }

   inner class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val txtFullName = itemView.findViewById<MaterialTextView>(R.id.text_view_fullName)
        val txtAge = itemView.findViewById<MaterialTextView>(R.id.text_view_age)
        val txtHeight = itemView.findViewById<MaterialTextView>(R.id.text_view_height)
        val txtWeight = itemView.findViewById<MaterialTextView>(R.id.text_view_weight)

        init{
            itemView.setOnClickListener(){
                if(selectedItems!=null){
                    if(selectedItems.get(adapterPosition,false)){
                        selectedItems.delete(adapterPosition)
                        it.isSelected = false
                    }
                    else{
                        if(selectedItems.size()<1){
                            selectedItems.put(adapterPosition,true)
                            it.isSelected = true
                        }
                    }
                }
                listener.onItemClick(adapterPosition)
                true
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val current = persons[position]
        holder.txtFullName.text = "${current.firstName} ${current.middleName} ${current.lastName}"
        holder.txtAge.text = "${current.age}"
        holder.txtHeight.text = "${current.height}"
        holder.txtWeight.text = "${current.weight}"
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