package com.example.sweetgram.ui.event_lenta

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.paging.PagedList
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.recycler.DatingEventAdapter
import javax.inject.Inject

class EventLentViewModel : ViewModel() {


    @Inject
    lateinit var lentaDataNode: LentaDataNode
    @Inject
    lateinit var datingEventAdapter: DatingEventAdapter
    @Inject
    lateinit var pagedListLiveData: LiveData<PagedList<DatingEvent>>

    init{
        SweetgramApplication.instance.injector.inject(this)
        datingEventAdapter.eventLentViewModel = this
    }

    fun deleteEventById(id: Long){
        Log.e("Delete from list", "id = $id")
        lentaDataNode.dataNode.deleteDatingEvent(id)
    }

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner) {
        lentaDataNode.initObserveResponse(lifeCycleOwner, pagedListLiveData)

        pagedListLiveData.observe(lifeCycleOwner) {
            datingEventAdapter.submitList(pagedListLiveData.value)
        }
    }
}