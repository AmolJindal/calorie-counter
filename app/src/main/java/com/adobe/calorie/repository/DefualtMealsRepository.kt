package com.adobe.calorie.repository

import androidx.lifecycle.LiveData
import com.adobe.calorie.data_source.MealsDataSource
import com.adobe.calorie.model.Meal
import com.adobe.calorie.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefualtMealsRepository(
    private val localDataSource: MealsDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MealsRepository {
    override val meals: LiveData<Result<List<Meal>>> = localDataSource.meals

    override suspend fun getMeal(id: Int): Meal {
        return localDataSource.getMeal(id)
    }

    override suspend fun addMeal(meal: Meal) = withContext(dispatcher) {
        localDataSource.addMeal(meal)
    }

    override suspend fun updateMeal(meal: Meal) = withContext(dispatcher) {
        localDataSource.updateMeal(meal)
    }

    override suspend fun deleteMeal(meal: Meal) = withContext(dispatcher) {
        localDataSource.deleteMeal(meal)
    }

    override suspend fun deleteAllMeals() = withContext(dispatcher) {
        localDataSource.deleteAllMeals()
    }
}
