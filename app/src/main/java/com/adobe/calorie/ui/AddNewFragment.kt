package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.databinding.FragmentAddNewBinding
import com.adobe.calorie.model.Meal
import com.adobe.calorie.model.MealType
import java.util.*

class AddNewFragment : Fragment() {

    lateinit var binding: FragmentAddNewBinding
    lateinit var viewModel: AddNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AddNewViewModel::class.java)
        initDropdown()
        registerListeners()
        return binding.root
    }

    private fun initDropdown() {
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf(
                MealType.BREAKFAST.name,
                MealType.LUNCH.name,
                MealType.DINNER.name,
                MealType.SNACKS.name
            )
        )

        binding.dropdownMenu.adapter = arrayAdapter
    }

    private fun registerListeners() {
        binding.done.setOnClickListener {
            val name = binding.mealName.text.toString()
            val calories = binding.calories.text.toString()

            if (name.isEmpty() || calories.isEmpty())
                Toast.makeText(it.context, "Please add name/calories", Toast.LENGTH_SHORT).show()
            else {
                viewModel.addMeal(
                    Meal(
                        UUID.randomUUID().hashCode(),
                        name,
                        MealType.from(binding.dropdownMenu.selectedItem.toString()),
                        calories.toInt()
                    )
                )
            }
        }
    }
}