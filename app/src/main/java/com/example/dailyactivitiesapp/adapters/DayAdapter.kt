package com.example.dailyactivitiesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyactivitiesapp.DailyActivities
import com.example.dailyactivitiesapp.DisplayMessageActivity
import com.example.dailyactivitiesapp.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DayAdapter(val dailyActivities: List<DailyActivities>, val context: Context) :
    RecyclerView.Adapter<DayAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.day_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTextView.text = dailyActivities[position].date
        dailyActivities[position].activities?.forEach {
            val chip = Chip(holder.chipGroup.context)
            chip.text = it.name
            chip.isClickable = true
            chip.setOnClickListener {
                val intent = Intent(context, DisplayMessageActivity::class.java).apply {
                    putExtra("activity_id", it.id)
                }
                context.startActivity(intent)
            }
            holder.chipGroup.addView(chip)
        }

    }

    override fun getItemCount(): Int {
        return dailyActivities.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val chipGroup: ChipGroup = view.findViewById(R.id.chipGroup)
    }

}