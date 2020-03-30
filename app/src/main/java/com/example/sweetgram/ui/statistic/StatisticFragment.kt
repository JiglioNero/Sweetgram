package com.example.sweetgram.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetgram.R
import com.example.sweetgram.databinding.FragmentStatisticBindingImpl


class StatisticFragment : Fragment() {

    private lateinit var statisticViewModel: StatisticViewModel
    private lateinit var binding: FragmentStatisticBindingImpl

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentStatisticBindingImpl>(inflater, R.layout.fragment_statistic, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        statisticViewModel =
            ViewModelProviders.of(this).get(StatisticViewModel::class.java)

        view?.let {
            val statRecycler = it.findViewById<RecyclerView>(R.id.stat_recycler)
            statRecycler.layoutManager = LinearLayoutManager(context)
            statRecycler.adapter = statisticViewModel.adapter

            val daysBefore = statisticViewModel.getCountOfDaysBeforeAnniversary()

            val progressBar = it.findViewById<ProgressBar>(R.id.anniversary_progress)
            progressBar.progress = if (365 - daysBefore > 60) 365 - daysBefore else 60


            val daysCountTextView = it.findViewById<TextView>(R.id.days_count)
            daysCountTextView.text = "${365 - daysBefore} ${resources.getString(R.string.stat_days)}"

            val activeHeart = it.findViewById<ImageView>(R.id.active_heart)
            val inactiveHeart = it.findViewById<ImageView>(R.id.inactive_heart)

            if (daysBefore <= 1){
                activeHeart.visibility = View.VISIBLE
                inactiveHeart.visibility = View.INVISIBLE
            }

            val constraintSet = ConstraintSet()
            constraintSet.clone(it.findViewById<ConstraintLayout>(R.id.constaraint_layout))
            val biasedValue = (progressBar.progress)/365f/2 - 0.05479452f
            constraintSet.setHorizontalBias(R.id.days_count, biasedValue)
            constraintSet.applyTo(it.findViewById(R.id.constaraint_layout) as ConstraintLayout?)

            it.findViewById<TextView>(R.id.days_befor_anniversary).text = daysBefore.toString()

            val periodSpinner = it.findViewById<Spinner>(R.id.period_spinner)
            val periodAdapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, Periods.map.map { object: KeyPair<Int, String>(it.key, resources.getString(it.key)){
                override fun toString(): String {
                    return resources.getString(it.key)
                }
            } })
            periodSpinner.adapter = periodAdapter
            periodSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val tv = view as TextView
                    tv.textSize = 10f
                    statisticViewModel.setStatisticByPeriod((periodSpinner.adapter.getItem(position) as KeyPair<Int, String>).first)
                }

            }

            val longSpinner = it.findViewById<Spinner>(R.id.long_spinner)
            val longAdapter = ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, Periods.map.map { object: KeyPair<Int, String>(it.key, resources.getString(it.key)){
                override fun toString(): String {
                    return resources.getString(it.key)
                }
            } })
            longSpinner.adapter = longAdapter
            longSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val tv = view as TextView
                    tv.textSize = 10f
                    val key = (longSpinner.adapter.getItem(position) as KeyPair<Int, String>).first
                    it.findViewById<TextView>(R.id.periods_count).setText(statisticViewModel.getCountOfPeriodsOfRelationship(key).toString())
                }

            }
        }
    }
}
