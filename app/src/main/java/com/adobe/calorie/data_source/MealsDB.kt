package com.adobe.calorie.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adobe.calorie.model.Meal

@Database(entities = [Meal::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun mealsDao(): MealDao
}
