package com.example.sweetgram.ui.statistic

abstract class KeyPair<A,B>(
    var first: A,
    var second: B
) {
    abstract override fun toString(): String
}