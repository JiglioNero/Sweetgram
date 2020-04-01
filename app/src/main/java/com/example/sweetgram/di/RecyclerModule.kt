package jiglionero.android.app.putonpompom.di

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.sweetgram.data.entitys.DatingEvent
import com.example.sweetgram.ui.event_lenta.LentaDataNode
import com.example.sweetgram.ui.event_lenta.recycler.DatingEventAdapter
import com.example.sweetgram.ui.profile.photo_gallery.recycler.GalleryAdapter
import dagger.Module
import dagger.Provides
import jiglionero.android.app.putonpompom.view.recycler.DatingEventDiffUtilItemCallback
import jiglionero.android.app.putonpompom.view.recycler.DatingEventSourceFactory
import jiglionero.android.app.putonpompom.view.recycler.GalleryDiffUtilItemCallback
import java.util.concurrent.Executors

@Module
class RecyclerModule{

    @Provides
    fun getDataSourceFactory(lentaDataNode: LentaDataNode): DatingEventSourceFactory {
        return DatingEventSourceFactory(lentaDataNode)
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

    // ----------------------------------------------------


    @Provides
    fun getDiffUtilItemCallbackGallery() : GalleryDiffUtilItemCallback {
        return GalleryDiffUtilItemCallback()
    }

    @Provides
    fun getGalleryAdapter(DiffUtilItemCallback: DatingEventDiffUtilItemCallback) : GalleryAdapter {
        return GalleryAdapter(DiffUtilItemCallback)
    }

}