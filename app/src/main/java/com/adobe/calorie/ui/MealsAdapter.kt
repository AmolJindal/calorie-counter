package com.adobe.calorie.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.adobe.calorie.model.Meal

class MealsAdapter : ListAdapter<Meal, MealVH>(MealDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealVH {
        return MealVH.from(parent)
    }

    override fun onBindViewHolder(holder: MealVH, position: Int) {
        holder.bind(getItem(position))
    }
}

class MealDiff : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem // will invoke equals()
    }
}
