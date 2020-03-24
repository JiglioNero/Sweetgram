package jiglionero.android.app.putonpompom.di

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.recycler.DatingEventAdapter
import dagger.Module
import dagger.Provides
import jiglionero.android.app.putonpompom.view.recycler.DatingEventDiffUtilItemCallback
import jiglionero.android.app.putonpompom.view.recycler.DatingEventSourceFactory
import java.util.concurrent.Executors

@Module
class RecyclerModule{

    @Provides
    fun getDataSourceFactory(): DatingEventSourceFactory {
        return DatingEventSourceFactory()
    }

    @Provides
    fun getPagedListConfig(): PagedList.Config{
        return PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    }

    @Provides
    fun getLivePagedList(sourceFactory: DatingEventSourceFactory, config: PagedList.Config) : LiveData<PagedList<DatingEvent>> {
        return LivePagedListBuilder(sourceFactory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    @Provides
    fun getWeatherDiffUtilItemCallback() : DatingEventDiffUtilItemCallback {
        return DatingEventDiffUtilItemCallback()
    }

    @Provides
    fun getAdapter(weatherDiffUtilItemCallback: DatingEventDiffUtilItemCallback) : DatingEventAdapter {
        return DatingEventAdapter(weatherDiffUtilItemCallback)
    }
}