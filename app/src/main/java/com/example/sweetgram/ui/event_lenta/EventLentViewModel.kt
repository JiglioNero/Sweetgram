package com.example.sweetgram.ui.event_lenta

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.paging.PagedList
import com.example.sweetgram.R
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

    lateinit var fragment: EventLentFragment

    init{
        SweetgramApplication.instance.injector.inject(this)
        datingEventAdapter.eventLentViewModel = this
    }

    fun deleteEventById(id: Long){
        lentaDataNode.dataNode.deleteDatingEvent(id)
    }

    fun redactEventById(id: Long){
        val bundleData = Bundle()
        bundleData.putSerializable("dating_event_id", id)
        findNavController(fragment).navigate(R.id.action_navigation_home_to_eventRedactorFragment, bundleData)
    }

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner) {
        lentaDataNode.initObserveResponse(lifeCycleOwner, pagedListLiveData)

        pagedListLiveData.observe(lifeCycleOwner) {
            datingEventAdapter.submitList(pagedListLiveData.value)
        }
    }
}