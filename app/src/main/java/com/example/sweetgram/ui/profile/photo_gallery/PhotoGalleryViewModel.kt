package com.example.sweetgram.ui.profile.photo_gallery

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.paging.PagedList
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.EventLentFragment
import com.example.sweetgram.ui.event_lenta.LentaDataNode
import com.example.sweetgram.ui.profile.photo_gallery.recycler.GalleryAdapter
import javax.inject.Inject

class PhotoGalleryViewModel : ViewModel() {
    @Inject
    lateinit var lentaDataNode: LentaDataNode
    @Inject
    lateinit var adapter: GalleryAdapter
    @Inject
    lateinit var pagedListLiveData: LiveData<PagedList<DatingEvent>>

    lateinit var fragment: EventLentFragment

    init{
        SweetgramApplication.instance.injector.inject(this)
    }

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner) {
        lentaDataNode.initObserveResponse(lifeCycleOwner, pagedListLiveData)

        pagedListLiveData.observe(lifeCycleOwner) {
            adapter.submitList(pagedListLiveData.value)
        }
    }
}
