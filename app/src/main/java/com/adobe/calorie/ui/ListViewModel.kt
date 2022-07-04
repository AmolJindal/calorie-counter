package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import com.adobe.calorie.repository.MealsRepository

class ListViewModel(mealsRepository: MealsRepository) : ViewModel() {
    val meals = mealsRepository.meals
}
