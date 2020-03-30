package com.example.sweetgram.data.entitys

class LoverCredentials(val username: String){
    constructor(lover: Lover) : this(lover.username) {
    }

    override fun toString(): String {
        return super.toString()
    }
}