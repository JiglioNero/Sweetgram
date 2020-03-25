package com.example.sweetgram.ui.event_redactor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.sweetgram.R
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.data.entitys.EventType
import com.example.sweetgram.databinding.EventRedactorFragmentBinding

class EventRedactorFragment : Fragment() {

    companion object {
        fun newInstance() = EventRedactorFragment()
    }

    private lateinit var viewModel: EventRedactorViewModel
    private lateinit var binding: EventRedactorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<EventRedactorFragmentBinding>(inflater, R.layout.event_redactor_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventRedactorViewModel::class.java)

        viewModel.datingEventField.set(DatingEvent())
        binding.redactVM = viewModel

        view?.let {v ->
            val spinner = v.findViewById<Spinner>(R.id.type_event)
            val adapter = ArrayAdapter<String>(context!!, R.layout.support_simple_spinner_dropdown_item, EventType.values().map { it.name })
            spinner.adapter = adapter

            val saveButton = v.findViewById<ImageView>(R.id.save_button)
            saveButton.setOnClickListener {
                viewModel.datingEventField.get()?.eventType = spinner.selectedItem as String
                viewModel.dataNode.saveDatingEvent(viewModel.datingEventField.get()!!)
                findNavController().navigate(R.id.action_add_event_to_event_lent)
            }
        }
    }

}
