package com.example.dailyactivitiesapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.dailyactivitiesapp.adapters.DayAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout?.setOnRefreshListener {
            loadData()
        }

    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.168:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = retrofit.create(Api::class.java)
        val call = request.days
        call.enqueue(object : Callback<List<DailyActivities>> {
            override fun onResponse(
                call: Call<List<DailyActivities>>?,
                response: Response<List<DailyActivities>>
            ) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.screen__main__toast_success__text),
                    Toast.LENGTH_SHORT
                ).show()

                val days = response.body()
                recyclerView?.adapter =
                    DayAdapter(days.sortedByDescending { it.date }.take(7), applicationContext)
                recyclerView?.adapter?.notifyDataSetChanged()
                recyclerView?.scheduleLayoutAnimation()

                swipeRefreshLayout?.let {
                    it.isRefreshing = false
                }

            }

            override fun onFailure(call: Call<List<DailyActivities>>?, t: Throwable?) {
                t?.message?.let {
                    Log.e("/activities", it)
                }

                Toast.makeText(
                    applicationContext,
                    getString(R.string.screen__main__toast_failure__text),
                    Toast.LENGTH_SHORT
                ).show()

                swipeRefreshLayout?.let {
                    it.isRefreshing = false
                }
            }


        })

    }

    override fun onResume() {
        super.onResume()

        loadData()
    }
}
