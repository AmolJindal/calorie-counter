package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import com.adobe.calorie.model.Meal
import com.adobe.calorie.result.Result

interface MealsDataSource {
    val meals: LiveData<Result<List<Meal>>>

    fun addMeal(meal: Meal)
    fun updateMeal(meal: Meal)
    fun deleteMeal(meal: Meal)
    fun deleteAllMeals()
}
