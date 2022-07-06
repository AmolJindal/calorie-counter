package com.adobe.calorie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.model.Meal
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.launch

class ViewDetailsViewModel(private val repository: MealsRepository, private val mealId: Int?) :
    ViewModel() {

    private val _mealData = MutableLiveData<Meal>()
    val mealData: LiveData<Meal> = _mealData

    init {
        viewModelScope.launch {
            mealId?.let {
                val meal = repository.getMeal(it)
                _mealData.value = meal
            }
        }
    }
}