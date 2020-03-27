package jiglionero.android.app.putonpompom.di

import android.content.Context
import androidx.room.Room
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.Database
import com.example.sweetgram.ui.event_lenta.LentaDataNode
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule{
    @Singleton
    @Provides
    fun getDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "Sweetgram-Database")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun getDataNode(database: Database): DataNode {
        return DataNode(database)
    }

    @Singleton
    @Provides
    fun getLentaDataNode(dataNode: DataNode): LentaDataNode {
        return LentaDataNode(dataNode)
    }
}