package com.example.sweetgram.ui.event_lenta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        val emptyMessage = fragmentEventLentView.findViewById<TextView>(R.id.emptyMessage)

        fragmentEventLentView.findViewById<FloatingActionButton>(R.id.add_event_button)
            .setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_eventRedactorFragment) }

        if (eventLentViewModel.pagedListLiveData.value?.size == 0){
            recyclerView.visibility = View.INVISIBLE
            emptyMessage.visibility = View.VISIBLE
        }
        else{
            recyclerView.visibility = View.VISIBLE
            emptyMessage.visibility = View.INVISIBLE
        }

        fragmentEventLentView.findViewById<SearchView>(R.id.search_field).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    eventLentViewModel.lentaDataNode.filter.value = it
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    eventLentViewModel.lentaDataNode.filter.value = it
                    return true
                }
                return false
            }

        })

        eventLentViewModel.pagedListLiveData.observe(this){
                if (it.size == 0){
                    recyclerView.visibility = View.INVISIBLE
                    emptyMessage.visibility = View.VISIBLE
                }
                else{
                    recyclerView.visibility = View.VISIBLE
                    emptyMessage.visibility = View.INVISIBLE
                }
        }
    }

}
