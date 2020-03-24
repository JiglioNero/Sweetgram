package com.example.sweetgram.data.entitys

import java.text.SimpleDateFormat
import java.util.*

data class DatingEvent (
    val location: String =  "",
    val date: Date = Date(),
    val userIconId: String = "",
    val eventText: String = "",
    val eventType: EventType = EventType.Date,
    val eventImageId: String = ""
){
    fun getFormattedDate(): String{
        return dateFormatter.format(date)
    }
    companion object {
        val dateFormatter = SimpleDateFormat("dd MMMM yyyy")
    }
}