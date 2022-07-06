package com.adobe.calorie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.model.Meal
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.launch

class AddNewViewModel(private val mealsRepository: MealsRepository) : ViewModel() {
    private val _mealAdded = MutableLiveData<Boolean>(false)
    val mealAdded: LiveData<Boolean> = _mealAdded

    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            mealsRepository.addMeal(meal)
            _mealAdded.value = true
        }
    }

    fun mealAddedHandled() {
        _mealAdded.value = false
    }
}
