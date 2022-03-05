package com.example.busscheduler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busscheduler.adapter.BusStopAdapter.BusStopViewHolder
import com.example.busscheduler.database.schedule.Schedule
import com.example.busscheduler.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.*


class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit) : ListAdapter<Schedule,BusStopViewHolder>(DiffCallback) {

    class BusStopViewHolder(private val binding:BusStopItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(schedule: Schedule){
            binding.stopNameTextView.text = schedule.stopName
            binding.arrivalTimeTextView.text = SimpleDateFormat("h:mm a").format(Date(schedule.arrivalTime.toLong() * 1000))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {

        val viewHolder = BusStopViewHolder(BusStopItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /*
    This is just an object that helps the ListAdapter determine which items
    in the new and old lists are different when updating the list.
    */
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Schedule>() {
            /**
             * checks if the object (or row in the database in your case) is the same by only checking the ID
             * */
            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem.id == newItem.id
            }

            /**
             * checks if all properties, not just the ID, are the same.
             * */
            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                return oldItem == newItem
            }
        }
    }
}