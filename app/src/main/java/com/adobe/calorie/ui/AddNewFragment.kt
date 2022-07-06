package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.CalorieApp
import com.adobe.calorie.CustomFactory
import com.adobe.calorie.databinding.FragmentAddNewBinding
import com.adobe.calorie.model.Meal
import com.adobe.calorie.model.MealType
import java.util.*

class AddNewFragment : Fragment() {

    private lateinit var binding: FragmentAddNewBinding
    private lateinit var viewModel: AddNewViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, CustomFactory {
            AddNewViewModel(CalorieApp.mealsRepository)
        })[AddNewViewModel::class.java]

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true) // NOT WORKING
        initDropdown()
        registerListeners()
        subscribeUi()
        return binding.root
    }

    private fun initDropdown() {
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf(
                MealType.BREAKFAST.toString(),
                MealType.LUNCH.toString(),
                MealType.DINNER.toString(),
                MealType.SNACKS.toString(),
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

    private fun subscribeUi() {
        viewModel.mealAdded.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager.popBackStack() // close this fragment
                viewModel.mealAddedHandled()
            }
        }
    }
}
