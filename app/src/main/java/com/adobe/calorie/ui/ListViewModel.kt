package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.launch

class ListViewModel(private val mealsRepository: MealsRepository) : ViewModel() {
    val meals = mealsRepository.meals

    fun deleteAllMeals() {
        viewModelScope.launch {
            mealsRepository.deleteAllMeals()
        }
    }
}
