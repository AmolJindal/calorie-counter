package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import com.adobe.calorie.repository.MealsRepository

class ListViewModel : ViewModel() {
    private val repository = MealsRepository()
    val meals = repository.mealsList
}
