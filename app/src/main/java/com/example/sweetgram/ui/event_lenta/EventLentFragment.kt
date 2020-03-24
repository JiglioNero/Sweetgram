package com.example.sweetgram.ui.event_lenta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.R

class EventLentFragment : Fragment() {

    private lateinit var eventLentViewModel: EventLentViewModel
    lateinit var fragmentEventLentView: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fragmentEventLentView = inflater.inflate(R.layout.fragment_event_lent, container, false)
        return fragmentEventLentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventLentViewModel = ViewModelProviders.of(this).get(EventLentViewModel::class.java)
        eventLentViewModel.initObserveResponse(this)
        val recyclerView = fragmentEventLentView.findViewById<RecyclerView>(R.id.recycler_event_lenta)
        recyclerView.adapter = eventLentViewModel.datingEventAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }

}
