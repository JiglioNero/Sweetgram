package jiglionero.android.app.putonpompom.view.recycler

import androidx.paging.DataSource
import com.example.sweetgram.data.entitys.DatingEvent

class DatingEventSourceFactory() : DataSource.Factory<Int, DatingEvent>() {

    override fun create(): DataSource<Int, DatingEvent> {
        return DatingEventPositionalDataSource()
    }
}