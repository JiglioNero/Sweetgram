package jiglionero.android.app.putonpompom.view.recycler

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PositionalDataSource
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.DatingEvent
import javax.inject.Inject

class DatingEventPositionalDataSource() : PositionalDataSource<DatingEvent>() {

    @Inject
    lateinit var dataNode: DataNode
    var filter: String = ""
    set(value)
    {
        field = value
        list = dataNode.getDatingEvents(filter)
    }
    var list: LiveData<List<DatingEvent>>

    init {
        SweetgramApplication.instance.injector.inject(this)
        list = dataNode.getDatingEvents(filter)
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<DatingEvent>
    ) {
        Log.d(
            ContentValues.TAG, "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                    ", requestedLoadSize = " + params.requestedLoadSize
        )
        var result: List<DatingEvent> = listOf()
        var size = 0
        if(list.value != null) {

            Log.e("SIZE", list.value!!.size.toString())
            var loadSize = params.requestedLoadSize
            val listSize = list.value!!.size
            if(params.requestedStartPosition + loadSize > listSize){
                loadSize = listSize - params.requestedStartPosition
            }
            result =
                list.value!!.subList(
                    params.requestedStartPosition,
                    params.requestedStartPosition + loadSize
                )
            size = listSize
        }
        if(params.placeholdersEnabled){
            callback.onResult(result, params.requestedStartPosition, size)
        }
        else {
            callback.onResult(result, params.requestedStartPosition)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DatingEvent>) {
        Log.d(
            ContentValues.TAG,
            "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize
        )
        var result: List<DatingEvent> = listOf()

        if(list.value != null) {
            var loadSize = params.loadSize
            val listSize = list.value!!.size
            if(params.startPosition + loadSize > listSize){
                loadSize = listSize - params.startPosition
            }
            result =
                list.value!!.subList(
                    params.startPosition,
                    params.startPosition + loadSize
                )
        }

        callback.onResult(result)
    }

}