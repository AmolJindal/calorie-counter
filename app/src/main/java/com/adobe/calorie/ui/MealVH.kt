package com.adobe.calorie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adobe.calorie.databinding.ViewHolderMealBinding
import com.adobe.calorie.model.Meal

class MealVH(private val binding: ViewHolderMealBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal, onClick: (Meal) -> Unit) {
        binding.apply {
            mealName.text = meal.name
            mealType.text = meal.type.toString()
            calories.text = meal.calories.toString()

            root.setOnClickListener {
                onClick.invoke(meal)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): MealVH {
            val binding =
                ViewHolderMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MealVH(binding)
        }
    }
}