package com.adobe.calorie.repository

import com.adobe.calorie.data_source.MealsLocalDataSource
import com.adobe.calorie.model.Meal

class MealsRepository(private val localDataSource: MealsLocalDataSource) {

    val mealsList = localDataSource.getMealsList()

    suspend fun addMeal(meal: Meal) = localDataSource.addMeal(meal)

    suspend fun removeMeal(meal: Meal) = localDataSource.deleteMeal(meal)
}
