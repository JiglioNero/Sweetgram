package jiglionero.android.app.putonpompom.view.recycler

import androidx.paging.DataSource
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.LentaDataNode

class DatingEventSourceFactory(var lentaDataNode: LentaDataNode) : DataSource.Factory<Int, DatingEvent>() {

    override fun create(): DataSource<Int, DatingEvent> {
        return DatingEventPositionalDataSource(lentaDataNode)
    }
}