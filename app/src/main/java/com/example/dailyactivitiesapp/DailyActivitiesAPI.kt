package com.example.dailyactivitiesapp

import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.Body

interface DailyActivitiesAPI {
    @GET("activities/{id}")
    fun getDailyActivity(@Path("id") id: Int): Call<JsonObject>

    @PUT("activities/{id}")
    fun updateDailyActivity(@Path("id") id: Int, @Body body: JsonObject): Call<JsonObject>
}