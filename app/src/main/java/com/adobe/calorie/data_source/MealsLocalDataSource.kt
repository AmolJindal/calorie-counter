package com.adobe.calorie.data_source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.adobe.calorie.model.Meal
import com.adobe.calorie.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsLocalDataSource(
    private val dao: MealDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    MealsDataSource {
    override val meals: LiveData<Result<List<Meal>>>
        get() {
            return dao.getAllMeals().map {
                Result.Success(it)
            }
        }

    override suspend fun getMeal(id: Int): Meal? = withContext(dispatcher) {
        dao.getMealById(id)
    }

    override fun getMealLiveDataById(id: Int): LiveData<Meal?> {
        return dao.getMealLiveDataById(id)
    }

    override suspend fun addMeal(meal: Meal) = withContext(dispatcher) {
        dao.addMeal(meal)
    }

    override suspend fun updateMeal(meal: Meal) = withContext(dispatcher) {
        dao.updateMeal(meal)
    }

    override suspend fun deleteMeal(meal: Meal) = withContext(dispatcher) {
        dao.removeMeal(meal)
    }

    override suspend fun deleteAllMeals() = withContext(dispatcher) {
        dao.removeAllMeals()
    }
}
