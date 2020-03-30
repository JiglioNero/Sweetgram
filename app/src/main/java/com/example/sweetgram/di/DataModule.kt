package jiglionero.android.app.putonpompom.di

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
    fun getDatabase(context: Context, onPreCreateCallback: RoomDatabase.Callback): Database {
        return Room.databaseBuilder(context, Database::class.java, "Sweetgram-Database")
            .allowMainThreadQueries()
            .addCallback(onPreCreateCallback)
            .build()
    }

    @Provides
    fun getOnCreateDatabaseCallback(): RoomDatabase.Callback{
        return object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val valuesEventType = arrayListOf<ContentValues>(
                    ContentValues().apply { put("name", "Date") },
                    ContentValues().apply { put("name", "Theather") },
                    ContentValues().apply { put("name", "Flowers") },
                    ContentValues().apply { put("name", "Cinema") },
                    ContentValues().apply { put("name", "Surprise") }
                )
                valuesEventType.forEach {
                    db.insert("EventType", OnConflictStrategy.REPLACE, it)
                }

                /*val ids = arrayListOf<Long>()
                val valuesAppUser = arrayListOf<ContentValues>(
                    ContentValues().apply { put("username", "Mikhail") },
                    ContentValues().apply { put("username", "Alesya") }
                )
                valuesAppUser.forEach {
                    ids.add(db.insert("Lover", OnConflictStrategy.ABORT, it))
                }

                val valuesRelationship = arrayListOf<ContentValues>(
                    ContentValues().apply {
                        put("lover1Id", ids[0])
                        put("lover2Id", ids[1])
                        put("dt", 1544383607000)
                    }
                )
                valuesRelationship.forEach {
                    db.insert("Relationship", OnConflictStrategy.ABORT, it)
                }*/
            }
        }
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