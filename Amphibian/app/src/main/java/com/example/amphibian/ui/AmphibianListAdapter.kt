package com.example.amphibian.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibian.R

class AmphibianListAdapter(private val context:Context,private val amphibians:List<String>):RecyclerView.Adapter<AmphibianListAdapter.AmphibianViewHolder>(){

    class AmphibianViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.amphibian_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmphibianViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return AmphibianViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AmphibianViewHolder, position: Int) {
//        holder.textView.text = amphibians[position]
        holder.textView.setOnClickListener{
            val action = AmphibianListFragmentDirections.actionAmphibianListFragmentToAmphibianDetailFragment(pos = position)
            holder.textView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return amphibians.size
    }
}