package com.example.sweetgram.ui.event_redactor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.sweetgram.R
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.databinding.FragmentEventRedactorBinding
import com.example.sweetgram.ui.activities.ImagePickerActivity
import pl.aprilapps.easyphotopicker.EasyImage


class EventRedactorFragment : Fragment() {

    companion object {
        fun newInstance() = EventRedactorFragment()
    }

    lateinit var viewModel: EventRedactorViewModel
    private lateinit var binding: FragmentEventRedactorBinding
    private lateinit var  easyImage: EasyImage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEventRedactorBinding>(inflater, R.layout.fragment_event_redactor, container, false)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        val path = data!!.extras!!.getString(ImagePickerActivity.IMAGE_PATH_KEY)!!
        Log.e("onActivityResult", "Path = $path")
        viewModel.datingEventField.get()?.eventImageId = path
        Log.e("datingEvent", "value = ${viewModel.datingEventField.get()}")
        viewModel.datingEventField.notifyChange()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EventRedactorViewModel::class.java)

        var event = DatingEvent()
        if (arguments?.containsKey("dating_event_id") != null && arguments?.containsKey("dating_event_id")!!){
            val id = arguments!!.getLong("dating_event_id")
            event = viewModel.dataNode.getDatingEventById(id)
        }
        viewModel.datingEventField.set(event)
        binding.redactVM = viewModel

        view?.let {v ->
            val spinner = v.findViewById<Spinner>(R.id.type_event)
            val adapter = ArrayAdapter<String>(context!!, R.layout.support_simple_spinner_dropdown_item, viewModel.eventTypes.map { it.name })
            spinner.adapter = adapter

            val saveButton = v.findViewById<ImageView>(R.id.save_button)
            saveButton.setOnClickListener {
                viewModel.datingEventField.get()?.eventType = spinner.selectedItem as String
                viewModel.dataNode.saveDatingEvent(viewModel.datingEventField.get()!!)

                findNavController().navigate(R.id.action_add_event_to_event_lent)
            }

            val deleteButton = v.findViewById<ImageView>(R.id.delete_button)
            deleteButton.setOnClickListener {
                findNavController().navigate(R.id.action_add_event_to_event_lent)
            }

            val addImageButton = v.findViewById<ImageView>(R.id.load_image)
            addImageButton.setOnClickListener {
                val intent = Intent(context, ImagePickerActivity::class.java)
                startActivityForResult(intent, 1)
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

    override fun onStop() {
        super.onStop()
        closeTheKeyBoard()
    }

}
