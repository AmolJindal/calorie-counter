package com.adobe.calorie

import android.app.Application
import androidx.room.Room
import com.adobe.calorie.data_source.Database
import com.adobe.calorie.data_source.MealsLocalDataSource
import com.adobe.calorie.repository.DefaultMealsRepository
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.Dispatchers

class CalorieApp : Application() {

    companion object {
        lateinit var mealsRepository: MealsRepository
    }

    override fun onCreate() {
        super.onCreate()

        // will be called once per app session
        createMealsRepository()
    }

    private fun createMealsRepository() {
        val db = Room.databaseBuilder(this, Database::class.java, "meals-db").build()
        val localDataSource = MealsLocalDataSource(db.mealsDao(), Dispatchers.IO)

        mealsRepository = DefaultMealsRepository(localDataSource, Dispatchers.IO)
    }
}
