package com.example.dailyactivitiesapp

import com.google.gson.annotations.SerializedName

data class DailyActivityField constructor(@SerializedName("field_name") var name: String,
                                          @SerializedName("field_value") var value: String)