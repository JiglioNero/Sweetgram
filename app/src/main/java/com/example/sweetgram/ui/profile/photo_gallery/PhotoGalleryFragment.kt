package com.example.sweetgram.ui.profile.photo_gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.R
import com.example.sweetgram.ui.profile.photo_gallery.recycler.GalleryAdapter

class PhotoGalleryFragment : Fragment() {

    private lateinit var viewModel: PhotoGalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
        viewModel.initObserveResponse(this)

        Log.e("Fragment", "PhotoGalleryFragment")

        view?.let {
            val recyclerView = it.findViewById<RecyclerView>(R.id.gallery_recycler)
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = viewModel.adapter

            Log.e("PhotoGalleryFragment", "size = ${(recyclerView.adapter as GalleryAdapter).itemCount}")
        }
    }

}
