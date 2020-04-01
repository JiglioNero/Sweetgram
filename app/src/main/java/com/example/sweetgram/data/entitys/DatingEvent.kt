package com.example.sweetgram.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class DatingEvent (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var location: String =  "",
    var dt: Long = Date().time,
    var relId: Long = 0,
    var eventText: String = "",
    var eventType: String = "Date",
    var eventImageId: String = ""
){
    var dt_txt = getFormattedDate()

    fun getFormattedDate(): String{
        return dateFormatter.format(Date(dt))
    }

    companion object {
        val dateFormatter = SimpleDateFormat("MMMM dd ", Locale.ENGLISH)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        this.javaClass.declaredFields.forEach {
            it.isAccessible = true
            sb.append("${it.type} ${it.name} = ${it.get(this)} \n")
        }
        return sb.toString()
    }
}