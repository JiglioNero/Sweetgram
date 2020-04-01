package com.example.sweetgram.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Relationship(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var lover1Id: Long = -1,
    var lover2Id: Long = -1,
    var relIconId: String = "",
    val dt: Long = Date().time
) {
    fun getFormattedDate(): String{
        return dateFormatter.format(Date(dt))
    }

    companion object {
        val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
    }
}