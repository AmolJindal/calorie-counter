package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.model.Meal
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewViewModel : ViewModel() {
    private val repository = MealsRepository(Dispatchers.IO)

    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            repository.addMeal(meal)
        }
    }
}