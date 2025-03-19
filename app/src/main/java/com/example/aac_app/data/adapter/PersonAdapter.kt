package com.example.aac_app.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_app.R
import com.example.aac_app.data.model.Person
import com.google.android.material.textview.MaterialTextView

class PersonAdapter(val context: Context, var persons: ArrayList<Person>): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.person_item, parent, false))
    }


    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val current = persons[position]
        holder.txtFullName.text = "${current.firstName} ${current.middleName} ${current.lastName}"
        holder.txtAge.text = "${current.age}"
        holder.txtHeight.text = "${current.height}"
        holder.txtWeight.text = "${current.weight}"

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtFullName = itemView.findViewById<MaterialTextView>(R.id.text_view_fullName)
        val txtAge = itemView.findViewById<MaterialTextView>(R.id.text_view_age)
        val txtHeight = itemView.findViewById<MaterialTextView>(R.id.text_view_height)
        val txtWeight = itemView.findViewById<MaterialTextView>(R.id.text_view_weight)
    }

}