package com.adobe.calorie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adobe.calorie.model.Meal
import com.adobe.calorie.model.MealType
import com.adobe.calorie.repository.MealsRepository
import kotlinx.coroutines.launch
import java.util.*

class AddEditViewModel(private val mealsRepository: MealsRepository, private var mealId: Int?) :
    ViewModel() {
    private val _mealAdded = MutableLiveData(false)
    val mealAdded: LiveData<Boolean> = _mealAdded

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _calories = MutableLiveData("")
    val calories: LiveData<String> = _calories

    private val _type = MutableLiveData(MealType.BREAKFAST)
    val type: LiveData<MealType> = _type

    private var newEntry = true

    init {
        viewModelScope.launch {
            mealsRepository.getMeal(mealId ?: 0)?.let {
                _name.value = it.name
                _calories.value = it.calories.toString()
                _type.value = it.type

                newEntry = false // update case
            }

            if (newEntry)
                mealId = null // will be set while adding meal to repository
        }
    }

    fun onDone() {
        val name = _name.value ?: ""
        val calories = _calories.value ?: ""
        val type = _type.value ?: MealType.BREAKFAST

        if (name.isEmpty() || calories.isEmpty()) {
            // TODO - move the string to resource file
            _error.postValue("Please add name/calories")
            return
        }

        viewModelScope.launch {
            mealsRepository.addMeal(
                Meal(
                    mealId ?: UUID.randomUUID().hashCode(), // if update case, use existing mealId
                    name,
                    type,
                    calories.toInt()
                )
            )
            _mealAdded.value = true
        }
    }

    fun setMealName(name: String) {
        if (_name.value != name)
            _name.value = name
    }

    fun setCaloriesCount(calories: String) {
        if (_calories.value != calories)
            _calories.value = calories
    }

    fun setMealType(type: MealType) {
        if (_type.value != type)
            _type.value = type
    }

    fun mealAddedHandled() {
        _mealAdded.postValue(false)
    }

    fun errorHandled() {
        _error.postValue(null)
    }
}
