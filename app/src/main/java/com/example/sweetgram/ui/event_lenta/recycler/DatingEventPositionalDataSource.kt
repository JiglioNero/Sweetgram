package jiglionero.android.app.putonpompom.view.recycler

import android.content.ContentValues
import android.util.Log
import androidx.paging.PositionalDataSource
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.LentaDataNode

class DatingEventPositionalDataSource(var lentaDataNode: LentaDataNode) : PositionalDataSource<DatingEvent>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<DatingEvent>
    ) {

        val list = lentaDataNode.actualEventList

        //Log.d(ContentValues.TAG, "List = ${list.value}")
        Log.d(
            ContentValues.TAG,
            "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                    ", requestedLoadSize = " + params.requestedLoadSize
        )
        var result: List<DatingEvent> = listOf()
        var size = 0
        if (list.value != null) {
            var loadSize = params.requestedLoadSize
            val listSize = list.value!!.size
            if (listSize > 0) {
                if (params.requestedStartPosition + loadSize > listSize) {
                    loadSize = listSize - params.requestedStartPosition
                }
                result =
                    list.value!!.subList(
                        params.requestedStartPosition,
                        params.requestedStartPosition + loadSize
                    )
            }
            size = listSize
        }
        if (params.placeholdersEnabled) {
            callback.onResult(result, params.requestedStartPosition, size)
        } else {
            callback.onResult(result, params.requestedStartPosition)
        }

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<DatingEvent>) {
        val list = lentaDataNode.actualEventList

        Log.d(
            ContentValues.TAG,
            "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize
        )
        var result: List<DatingEvent> = listOf()

        if (list.value != null) {
            var loadSize = params.loadSize
            val listSize = list.value!!.size
            if (listSize > 0) {
                if (params.startPosition + loadSize > listSize) {
                    loadSize = listSize - params.startPosition
                }
                result =
                    list.value!!.subList(
                        params.startPosition,
                        params.startPosition + loadSize
                    )
            }
        }

        callback.onResult(result)

    }

}