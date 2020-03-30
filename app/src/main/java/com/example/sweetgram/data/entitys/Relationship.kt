package com.example.sweetgram.data.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Relationship(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val lover1Id: Long = -1,
    val lover2Id: Long = -1,
    val dt: Long = Date().time
) {}