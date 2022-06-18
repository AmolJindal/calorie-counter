package com.adobe.calorie

import android.app.Application
import androidx.room.Room
import com.adobe.calorie.data_source.Database

class CalorieApp : Application() {

    companion object {
        lateinit var db: Database
    }

    override fun onCreate() {
        super.onCreate()

        // will be constructed once
        db = Room.databaseBuilder(this, Database::class.java, "meals-db").build()
    }
}
