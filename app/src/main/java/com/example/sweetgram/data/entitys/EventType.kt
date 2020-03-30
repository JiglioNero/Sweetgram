package com.example.sweetgram.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventType(
    @PrimaryKey
    val name: String
)