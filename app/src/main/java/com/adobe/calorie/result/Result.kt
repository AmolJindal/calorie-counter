package com.adobe.calorie.result

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: String?) : Result<Nothing>()
    data class Loading(val status: String) : Result<Nothing>() // status can be enum
}

// As Result is sealed class, no new derived class can be created outside the module
