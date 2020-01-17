package com.example.dailyactivitiesapp

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @get:GET("/days")
    val days: Call<List<DailyActivities>>
}
