package com.adobe.calorie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adobe.calorie.model.Meal

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<Pair<State, Meal?>>(Pair(State.LIST, null))
    val state: LiveData<Pair<State, Meal?>> = _state

    fun addNewMeal() {
        _state.postValue(Pair(State.ADD_EDIT, null))
    }

    fun viewDetails(meal: Meal) {
        _state.postValue(Pair(State.VIEW, meal))
    }

    enum class State {
        LIST,
        ADD_EDIT,
        VIEW
    }
}