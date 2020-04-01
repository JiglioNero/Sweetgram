package com.example.sweetgram.ui.event_lenta

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

    private lateinit var viewModel: EventLentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_lent, container, false)
    }

    override fun onResume() {
        super.onResume()

        view?.let{
            it.findViewById<RecyclerView>(R.id.recycler_event_lenta).adapter?.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EventLentViewModel::class.java)
        viewModel.initObserveResponse(this)
        viewModel.fragment = this

        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_event_lenta)
            recyclerView.adapter = viewModel.datingEventAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
            val emptyMessage = it.findViewById<TextView>(R.id.emptyMessage)

            it.findViewById<FloatingActionButton>(R.id.add_event_button)
                .setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_eventRedactorFragment) }

            it.findViewById<SearchView>(R.id.search_field).setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.lentaDataNode.filter.value = it
                        closeTheKeyBoard()
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        viewModel.lentaDataNode.filter.value = it
                        return true
                    }
                    return false
                }

            })

            if (viewModel.pagedListLiveData.value?.size == 0){
                recyclerView.visibility = View.INVISIBLE
                emptyMessage.visibility = View.VISIBLE
            }
            else{
                recyclerView.visibility = View.VISIBLE
                emptyMessage.visibility = View.INVISIBLE
            }

            viewModel.pagedListLiveData.observe(this){
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

    private fun closeTheKeyBoard(){
        super.onStop()
        val v = view?.findFocus()
        v?.let {
            val imm =
                context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(v!!.applicationWindowToken, 0)
        }
    }

}
