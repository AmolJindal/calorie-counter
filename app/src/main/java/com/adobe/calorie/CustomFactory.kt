package com.adobe.calorie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomFactory<T : ViewModel>(private val func: () -> T) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return func() as T
    }
}
