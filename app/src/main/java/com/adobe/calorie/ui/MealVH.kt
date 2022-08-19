package com.adobe.calorie.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adobe.calorie.databinding.ViewHolderMealBinding
import com.adobe.calorie.model.Meal

class MealVH(
    private val binding: ViewHolderMealBinding,
    private val interactionListener: InteractionListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal) {
        binding.apply {
            mealName.text = meal.name
            mealType.text = meal.type.toString()
            calories.text = meal.calories.toString()

            root.setOnClickListener {
                interactionListener.onClick(meal)
            }

            root.setOnLongClickListener {
                interactionListener.onLongClick(meal)
                true
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup, interactionListener: InteractionListener): MealVH {
            val binding =
                ViewHolderMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MealVH(binding, interactionListener)
        }
    }

    interface InteractionListener {
        fun onClick(meal: Meal)
        fun onLongClick(meal: Meal)
    }
}