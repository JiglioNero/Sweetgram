package com.example.sweetgram.ui.statistic.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.data.entitys.EventType
import com.example.sweetgram.databinding.StatisticElementBindingImpl

class StatisticItemAdapter : RecyclerView.Adapter<StatisticItemAdapter.StatisticItemHolder>() {

    var listOfPairs = ArrayList<Pair<EventType, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticItemHolder {
        val binding = DataBindingUtil.inflate<StatisticElementBindingImpl>(
            LayoutInflater.from(parent.context),
            com.example.sweetgram.R.layout.statistic_element,
            parent,
            false)

        return StatisticItemHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return listOfPairs.size
    }

    override fun onBindViewHolder(holder: StatisticItemHolder, position: Int) {
        holder.onBind(listOfPairs[position])
    }

    inner class StatisticItemHolder(itemView: View, val binding: StatisticElementBindingImpl): RecyclerView.ViewHolder(itemView){
        fun onBind(pair: Pair<EventType, Int>){
            binding.eventName = pair.first.name
            binding.eventCount = pair.second
        }
    }
}