package com.example.sweetgram.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.databinding.EventElementBinding
import com.example.sweetgram.entitys.DatingEvent

class DatingEventAdapter(diffUtilCallback: DiffUtil.ItemCallback<DatingEvent>) : PagedListAdapter<DatingEvent, DatingEventAdapter.DateEventHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateEventHolder {
        val binding = DataBindingUtil.inflate<EventElementBinding>(
            LayoutInflater.from(parent.context),
            com.example.sweetgram.R.layout.event_element,
            parent,
            false)
        return DateEventHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: DateEventHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DateEventHolder(itemView: View, val binding: EventElementBinding) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: DatingEvent?) {
            if (event!=null) {
                binding.event = event
            }
        }
    }

}