package com.example.sweetgram.ui.event_lenta

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.sweetgram.MainActivity
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.recycler.DatingEventAdapter
import javax.inject.Inject

class EventLentViewModel : ViewModel() {

    private val _filter = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _filter

    @Inject
    lateinit var datingEventAdapter: DatingEventAdapter
    @Inject
    lateinit var pagedListLiveData: LiveData<PagedList<DatingEvent>>
    private val _dataNode: DataNode = DataNode

    init{
        MainActivity.instance.injector.inject(this)
    }

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner) {
        pagedListLiveData.observe(lifeCycleOwner, Observer {
            datingEventAdapter.submitList(it)
        })
    }
}