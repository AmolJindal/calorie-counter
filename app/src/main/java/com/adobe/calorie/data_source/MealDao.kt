package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.adobe.calorie.model.Meal

// TODO - how to handle conflicts?

@Dao
interface MealDao {
    // TODO - why are these not suspend functions?

    @Insert(onConflict = REPLACE)
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM Meals")
    fun getAllMeals(): LiveData<List<Meal>>

    // TODO - add clearAll()

    @Delete
    fun removeMeal(meal: Meal)

    @Query("DELETE FROM Meals")
    fun removeAllMeals()

    // TODO - we can add a filter for meals above 500 calories
}
