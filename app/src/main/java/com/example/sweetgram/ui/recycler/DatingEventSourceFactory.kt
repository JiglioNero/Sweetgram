package jiglionero.android.app.putonpompom.view.recycler

import androidx.paging.DataSource
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.entitys.DatingEvent

class DatingEventSourceFactory(private val dataNode: DataNode) : DataSource.Factory<Int, DatingEvent>() {

    override fun create(): DataSource<Int, DatingEvent> {
        return DatingEventPositionalDataSource(dataNode)
    }
}