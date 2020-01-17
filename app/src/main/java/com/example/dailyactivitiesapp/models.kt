package com.example.dailyactivitiesapp


data class DailyActivities(val id: Int, val date: String?, val activities: List<Activity>?)
data class Activity(val id: Int, val name: String?)