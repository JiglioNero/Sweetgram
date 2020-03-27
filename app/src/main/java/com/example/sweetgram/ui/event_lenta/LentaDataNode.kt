package com.example.sweetgram.ui.event_lenta

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent

class LentaDataNode(val dataNode: DataNode) {

    var filter = MutableLiveData("")
    var actualEventList = dataNode.getDatingEvents(filter.value)

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner, pagedList: LiveData<PagedList<DatingEvent>>) {
        filter.observe(lifeCycleOwner, Observer {
            actualEventList = dataNode.getDatingEvents(it)
            actualEventList.observe(lifeCycleOwner, Observer{
                pagedList.value?.dataSource?.invalidate()
            })
        })
        actualEventList.observe(lifeCycleOwner, Observer{
            pagedList.value?.dataSource?.invalidate()
        })
    }
}