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

enum class MealType(private val str: String) {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACKS("Snacks");

    override fun toString(): String {
        return str
    }

    companion object {
        fun from(str: String): MealType {
            return when (str) {
                BREAKFAST.str -> BREAKFAST
                LUNCH.str -> LUNCH
                DINNER.str -> DINNER
                SNACKS.str -> SNACKS
                else -> BREAKFAST
            }
        }
    }
}
