package com.adobe.calorie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adobe.calorie.databinding.ViewHolderMealBinding
import com.adobe.calorie.model.Meal

class MealVH(private val binding: ViewHolderMealBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal) {
        binding.apply {
            mealName.text = meal.name
            mealType.text = meal.type.name
            calories.text = meal.calories.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup): MealVH {
            val binding = ViewHolderMealBinding.inflate(LayoutInflater.from(parent.context))
            return MealVH(binding)
        }
    }
}