package com.adobe.calorie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals")
data class Meal(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    val type: MealType,
    val calories: Int
)

enum class MealType(name: String) {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACKS("Snacks")
}
