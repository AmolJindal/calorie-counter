package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.adobe.calorie.model.Meal
import com.adobe.calorie.result.Result

class MealsLocalDataSource(private val dao: MealDao) : MealsDataSource {
    override val meals: LiveData<Result<List<Meal>>>
        get() {
            return dao.getAllMeals().map {
                Result.Success(it)
            }
        }

    override fun addMeal(meal: Meal) {
        dao.addMeal(meal)
    }

    override fun updateMeal(meal: Meal) {
        TODO("Not yet implemented")
    }

    override fun deleteMeal(meal: Meal) {
        dao.removeMeal(meal)
    }

    override fun deleteAllMeals() {
        dao.removeAllMeals()
    }
}
