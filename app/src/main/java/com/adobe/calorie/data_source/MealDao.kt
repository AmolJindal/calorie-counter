package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.adobe.calorie.model.Meal

// TODO - how to handle conflicts?

@Dao
interface MealDao {
    // TODO - why are these not suspend functions?

    @Insert(onConflict = REPLACE)
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM Meals")
    fun getAllMeals(): LiveData<List<Meal>>

    @Query("SELECT * FROM Meals WHERE id = :id")
    suspend fun getMealById(id: Int): Meal?

    @Query("SELECT * FROM Meals WHERE id = :id")
    fun getMealLiveDataById(id: Int): LiveData<Meal?>

    // TODO - add clearAll()

    @Update
    fun updateMeal(meal: Meal)

    @Delete
    fun removeMeal(meal: Meal)

    @Query("DELETE FROM Meals")
    fun removeAllMeals()

    // TODO - we can add a filter for meals above 500 calories
}
