package com.example.sweetgram.ui.event_lenta.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.databinding.EventElementBindingImpl

class DatingEventAdapter(diffUtilCallback: DiffUtil.ItemCallback<DatingEvent>) : PagedListAdapter<DatingEvent, DatingEventAdapter.DateEventHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateEventHolder {
        val binding = DataBindingUtil.inflate<EventElementBindingImpl>(
            LayoutInflater.from(parent.context),
            com.example.sweetgram.R.layout.event_element,
            parent,
            false)
        return DateEventHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: DateEventHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class DateEventHolder(itemView: View, val binding: EventElementBindingImpl) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: DatingEvent) {
                binding.event = event
        }
    }

}