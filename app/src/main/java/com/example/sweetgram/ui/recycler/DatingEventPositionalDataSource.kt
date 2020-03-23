package jiglionero.android.app.putonpompom.view.recycler

import android.content.ContentValues
import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.entitys.DatingEvent

class DatingEventPositionalDataSource(private val dataNode: DataNode) : PositionalDataSource<DatingEvent>() {


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
        /*if(dataNode.forecastList.value != null) {
            var loadSize = params.requestedLoadSize
            val listSize = dataNode.forecastList.value!!.size
            if(params.requestedStartPosition + loadSize > listSize){
                loadSize = listSize - params.requestedStartPosition
            }
            result =
                dataNode.forecastList.value!!.subList(
                    params.requestedStartPosition,
                    params.requestedStartPosition + loadSize
                )
            size = listSize
        }*/
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
        /*if(dataNode.forecastList.value != null) {
            var loadSize = params.loadSize
            val listSize = dataNode.forecastList.value!!.size
            if(params.startPosition + loadSize > listSize){
                loadSize = listSize - params.startPosition
            }
            result =
                dataNode.forecastList.value!!.subList(
                    params.startPosition,
                    params.startPosition + loadSize
                )
        }*/
        callback.onResult(result)
    }

}