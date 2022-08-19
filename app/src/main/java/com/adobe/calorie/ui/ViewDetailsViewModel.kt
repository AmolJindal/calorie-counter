package com.adobe.calorie.ui

import androidx.lifecycle.ViewModel
import com.adobe.calorie.repository.MealsRepository

class ViewDetailsViewModel(repository: MealsRepository, mealId: Int?) :
    ViewModel() {

    val mealData = repository.getMealLiveDataById(mealId ?: 0)
}
