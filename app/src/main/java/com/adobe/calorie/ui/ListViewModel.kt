package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.Dispatchers

class ListViewModel : ViewModel() {
    // TODO - I think repository should be singleton
    private val repository = MealsRepository(Dispatchers.IO)
    val meals = repository.mealsList
}
