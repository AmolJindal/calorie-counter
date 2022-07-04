package com.adobe.calorie

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.ui.AddNewViewModel
import com.adobe.calorie.ui.ListViewModel

val Fragment.customFactory: ViewModelProvider.Factory
    get() {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(AddNewViewModel::class.java))
                    AddNewViewModel(CalorieApp.mealsRepository) as T
                else if (modelClass.isAssignableFrom(ListViewModel::class.java))
                    ListViewModel(CalorieApp.mealsRepository) as T
                else
                    throw IllegalArgumentException("unknown class name ${modelClass.name}")
            }
        }
    }