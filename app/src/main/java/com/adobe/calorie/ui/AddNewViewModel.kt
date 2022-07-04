package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.model.Meal
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.launch

class AddNewViewModel(private val mealsRepository: MealsRepository) : ViewModel() {
    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            mealsRepository.addMeal(meal)
        }
    }
}
