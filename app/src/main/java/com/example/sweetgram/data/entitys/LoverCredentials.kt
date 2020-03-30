package com.example.sweetgram.data.entitys

import java.io.Serializable

class LoverCredentials(val username: String): Serializable{
    constructor(lover: Lover) : this(lover.username) {
    }

    override fun toString(): String {
        return super.toString()
    }
}