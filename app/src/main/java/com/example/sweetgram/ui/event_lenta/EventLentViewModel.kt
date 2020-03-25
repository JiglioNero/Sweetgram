package com.example.sweetgram.ui.event_lenta

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.recycler.DatingEventAdapter
import jiglionero.android.app.putonpompom.view.recycler.DatingEventPositionalDataSource
import javax.inject.Inject

class EventLentViewModel : ViewModel() {

    val filter = MutableLiveData<String>().apply { value = "" }

    @Inject
    lateinit var datingEventAdapter: DatingEventAdapter
    @Inject
    lateinit var pagedListLiveData: LiveData<PagedList<DatingEvent>>
    @Inject
    lateinit var dataNode: DataNode

    init{
        SweetgramApplication.instance.injector.inject(this)
    }

    fun initObserveResponse(lifeCycleOwner: LifecycleOwner) {
        filter.observe(lifeCycleOwner) {value ->
            pagedListLiveData.value?.dataSource?.let {
                if (it is DatingEventPositionalDataSource)
                    it.filter = value
            }
        }

        pagedListLiveData.value?.dataSource?.let {
            if (it is DatingEventPositionalDataSource)
                it.list.observe(lifeCycleOwner) { list ->
                    it.invalidate()
                }
        }

        pagedListLiveData.observe(lifeCycleOwner) {
            datingEventAdapter.submitList(it)
        }
    }
}