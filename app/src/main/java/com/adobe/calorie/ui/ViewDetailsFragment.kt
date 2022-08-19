package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.CalorieApp
import com.adobe.calorie.CustomFactory
import com.adobe.calorie.databinding.FragmentViewDetailsBinding

class ViewDetailsFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsBinding
    private lateinit var viewModel: ViewDetailsViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, CustomFactory {
            ViewDetailsViewModel(CalorieApp.mealsRepository, arguments?.getInt(KEY_MEAL_ID))
        })[ViewDetailsViewModel::class.java]

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        subscribeUi()
        registerListeners()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.mealData.observe(viewLifecycleOwner) {
            binding.apply {
                mealName.text = it?.name
                calories.text = it?.calories.toString()
                mealType.text = it?.type.toString()
            }
        }
    }

    private fun registerListeners() {
        binding.edit.setOnClickListener {
            mainViewModel.editMeal(arguments?.getInt(KEY_MEAL_ID))
        }
    }

    companion object {
        const val KEY_MEAL_ID = "MEAL_ID"
    }
}
