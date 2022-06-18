package com.adobe.calorie.data_source

import com.adobe.calorie.CalorieApp
import com.adobe.calorie.model.Meal

class MealsLocalDataSource {
    suspend fun addMeal(meal: Meal) = CalorieApp.db.mealsDao().addMeal(meal)

    suspend fun deleteMeal(meal: Meal) = CalorieApp.db.mealsDao().removeMeal(meal)

    fun getMealsList() = CalorieApp.db.mealsDao().getAllMeals()
}
