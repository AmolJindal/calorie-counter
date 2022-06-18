package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.adobe.calorie.model.Meal

// TODO - how to handle conflicts?

@Dao
interface MealDao {
    @Insert
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM Meals")
    fun getAllMeals(): LiveData<List<Meal>>

    // TODO - add clearAll()

    @Delete
    fun removeMeal(meal: Meal)
}