package com.example.sweetgram.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sweetgram.R

class StatisticFragment : Fragment() {

    private lateinit var statisticViewModel: StatisticViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        statisticViewModel =
                ViewModelProviders.of(this).get(StatisticViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)

        return root
    }
}
