package com.example.sweetgram.ui.statistic

import com.example.sweetgram.R

object Periods {
    val map = HashMap<Int, Long>().apply{
        put(R.string.stat_day, 86400000)
        put(R.string.stat_week, 86400000 * 7)
        put(R.string.stat_month, 86400000L * 30)
        put(R.string.stat_year, 86400000L * 365)
    }

}