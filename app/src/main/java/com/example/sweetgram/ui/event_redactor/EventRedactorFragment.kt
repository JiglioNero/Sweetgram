package com.example.sweetgram.ui.event_redactor

import android.content.Intent
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
import com.example.sweetgram.ui.activities.MainActivity
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource


class EventRedactorFragment : Fragment() {

    companion object {
        fun newInstance() = EventRedactorFragment()
    }

    private lateinit var viewModel: EventRedactorViewModel
    private lateinit var binding: EventRedactorFragmentBinding
    private lateinit var  easyImage: EasyImage


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<EventRedactorFragmentBinding>(inflater, R.layout.event_redactor_fragment, container, false)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(requestCode, resultCode, data, context as MainActivity, object : EasyImage.Callbacks{
            override fun onCanceled(source: MediaSource) {

            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                error.printStackTrace()
            }

            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                viewModel.datingEventField.get()?.eventImageId = imageFiles[0].file.absolutePath
                viewModel.datingEventField.notifyChange()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProviders.of(this).get(EventRedactorViewModel::class.java)

        viewModel.datingEventField.set(DatingEvent())
        binding.redactVM = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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

            val addImageButton = v.findViewById<ImageView>(R.id.load_image)
            addImageButton.setOnClickListener {
                easyImage =
                    EasyImage.Builder(context!!) // Chooser only
                    // Will appear as a system chooser title, DEFAULT empty string
                    //.setChooserTitle("Pick media")
                    // Will tell chooser that it should show documents or gallery apps
                    //.setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)  you can use this or the one below
                    //.setChooserType(ChooserType.CAMERA_AND_GALLERY)
                    // Setting to true will cause taken pictures to show up in the device gallery, DEFAULT false
                    .setCopyImagesToPublicGalleryFolder(true) // Sets the name for images stored if setCopyImagesToPublicGalleryFolder = true
                    .setFolderName("Sweetgram") // Allow multiple picking
                    .allowMultiple(false)
                    .build()

                easyImage.openChooser(this)
            }
        }
    }

}
