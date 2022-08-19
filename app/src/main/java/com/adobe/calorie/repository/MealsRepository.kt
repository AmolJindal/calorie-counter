package com.adobe.calorie.repository

import androidx.lifecycle.LiveData
import com.adobe.calorie.model.Meal
import com.adobe.calorie.result.Result

/**
 * This repository can be implemented for normal app and testing separately.
 */
interface MealsRepository {
    val meals: LiveData<Result<List<Meal>>>

    suspend fun getMeal(id: Int): Meal?
    fun getMealLiveDataById(id: Int): LiveData<Meal?>
    suspend fun addMeal(meal: Meal)
    suspend fun updateMeal(meal: Meal)
    suspend fun deleteMeal(meal: Meal)
    suspend fun deleteAllMeals()
}
