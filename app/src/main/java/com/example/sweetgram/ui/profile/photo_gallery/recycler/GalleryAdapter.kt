package com.example.sweetgram.ui.profile.photo_gallery.recycler

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.data.entitys.DatingEvent
import com.squareup.picasso.Picasso
import java.io.File

class GalleryAdapter(diffUtilCallback: DiffUtil.ItemCallback<DatingEvent>) : PagedListAdapter<DatingEvent, GalleryAdapter.ImageHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {

        return ImageHolder(ImageView(parent.context))
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(datingEvent: DatingEvent) {
            Picasso.get().load(File(datingEvent.eventImageId)).into(itemView as ImageView)
        }
    }

}