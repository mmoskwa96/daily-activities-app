package com.example.dailyactivitiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DisplayMessageActivity: AppCompatActivity() {
    private val baseUrl = "http://192.168.8.110:3000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daily_activity_list)

        val intent = intent
        val id = intent.extras?.getInt("activity_id")

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val dailyActivitiesAPI = retrofit.create(DailyActivitiesAPI::class.java)

        if (id != null) {
            val call = dailyActivitiesAPI.getDailyActivity(id)
            getData(call)
        }
    }

    private fun getData(call: Call<JsonObject>) {
        call.enqueue(object: Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("MainActivity", t.message)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val jsonObject = response.body()
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@DisplayMessageActivity)
                    recyclerView.adapter = DailyActivityFieldsAdapter(jsonObject)
                } else {
                    Log.d("MainActivity", response.message())
                }
            }
        })
    }
}