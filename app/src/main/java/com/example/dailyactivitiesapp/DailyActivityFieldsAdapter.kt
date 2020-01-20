package com.example.dailyactivitiesapp

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2    .Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailyActivityFieldsAdapter(private val jsonObject: JsonObject):
        RecyclerView.Adapter<DailyActivityFieldsAdapter.DailyActivityFieldViewHolder>() {

    private val fieldArray: Array<DailyActivityField> = Gson().fromJson(jsonObject.get("fields"),
        Array<DailyActivityField>::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.43.168:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val dailyActivitiesAPI = retrofit.create(DailyActivitiesAPI::class.java)

    class DailyActivityFieldViewHolder(itemView: View, val textWatcher: FieldValueTextWatcher):
            RecyclerView.ViewHolder(itemView) {
        val fieldTitle: TextView = itemView.findViewById(R.id.field_title)
        val fieldValue: EditText = itemView.findViewById(R.id.field_value)

        init {
            fieldValue.addTextChangedListener(textWatcher)
        }
    }

    inner class FieldValueTextWatcher: TextWatcher {
        private var currentPosition = -1

        fun updatePosition(position: Int) {
            this.currentPosition = position
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s != null) {
                fieldArray[currentPosition].value = s.toString()

                jsonObject.remove("fields")

                val jsonArray = JsonArray()
                for (element in fieldArray) {
                    val jsonElement = JsonObject()
                    jsonElement.addProperty("field_name", element.name)
                    jsonElement.addProperty("field_value", element.value)
                    jsonArray.add(jsonElement)
                }

                jsonObject.add("fields", jsonArray)

                val call = dailyActivitiesAPI.updateDailyActivity(jsonObject.get("id").asInt, jsonObject)
                call.enqueue(object: Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        // no action
                    }

                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        // no action
                    }
                })
            }
        }

        override fun afterTextChanged(s: Editable?) {
            // no action
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // no action
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyActivityFieldViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_activity_field_item, parent, false)
        return DailyActivityFieldViewHolder(itemView, FieldValueTextWatcher())
    }

    override fun getItemCount(): Int {
        return fieldArray.size
    }

    override fun onBindViewHolder(holder: DailyActivityFieldViewHolder, position: Int) {
        holder.textWatcher.updatePosition(position)
        holder.fieldTitle.text = fieldArray[position].name
        holder.fieldValue.setText(fieldArray[position].value, TextView.BufferType.EDITABLE)
    }
}