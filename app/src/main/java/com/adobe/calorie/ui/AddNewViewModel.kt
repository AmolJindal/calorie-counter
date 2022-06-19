package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import com.adobe.calorie.model.Meal
import com.adobe.calorie.repository.MealsRepository

class AddNewViewModel : ViewModel() {
    private val repository = MealsRepository()

    fun addMeal(meal: Meal) {

    }
}