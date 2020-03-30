package com.example.sweetgram.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lover(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var username: String = "-1"
){
    fun getCredentials(): LoverCredentials{
        return LoverCredentials(this)
    }
}