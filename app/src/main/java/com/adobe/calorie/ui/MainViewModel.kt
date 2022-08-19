package com.adobe.calorie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData<Pair<State, Int?>?>(Pair(State.LIST, null))
    val state: LiveData<Pair<State, Int?>?> = _state

    fun addNewMeal() {
        _state.postValue(Pair(State.ADD_EDIT, null))
    }

    fun viewDetails(mealId: Int?) {
        _state.postValue(Pair(State.VIEW, mealId))
    }

    fun editMeal(mealId: Int?) {
        _state.postValue(Pair(State.ADD_EDIT, mealId))
    }

    fun stateHandled() {
        _state.postValue(null)
    }

    enum class State {
        LIST,
        ADD_EDIT,
        VIEW
    }
}
