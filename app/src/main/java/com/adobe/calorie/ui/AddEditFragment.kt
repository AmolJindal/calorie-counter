package com.adobe.calorie.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.CalorieApp
import com.adobe.calorie.CustomFactory
import com.adobe.calorie.databinding.FragmentAddNewBinding
import com.adobe.calorie.model.MealType

class AddEditFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentAddNewBinding
    private lateinit var viewModel: AddEditViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, CustomFactory {
            AddEditViewModel(CalorieApp.mealsRepository, arguments?.getInt(KEY_MEAL_ID))
        })[AddEditViewModel::class.java]

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
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
        binding.apply {
            mealName.addTextChangedListener(this@AddEditFragment)
            calories.addTextChangedListener(this@AddEditFragment)

            dropdownMenu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.setMealType(MealType.from(dropdownMenu.selectedItem.toString()))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // no op
                }
            }

            done.setOnClickListener {
                viewModel.onDone()
            }
        }
    }

    private fun subscribeUi() {
        viewModel.apply {
            error.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
                errorHandled()
            }

            name.observe(viewLifecycleOwner) {
                // We only want to set the value from viewModel if we are setting this text
                // for the first time after entering this fragment.
                if (binding.mealName.text.isEmpty())
                    binding.mealName.setText(it)
            }

            calories.observe(viewLifecycleOwner) {
                // We only want to set the value from viewModel if we are setting this text
                // for the first time after entering this fragment.
                if (binding.calories.text.isEmpty())
                    binding.calories.setText(it)
            }

            type.observe(viewLifecycleOwner) {
                binding.dropdownMenu.setSelection(it.ordinal)
            }

            mealAdded.observe(viewLifecycleOwner) {
                if (it) {
                    requireActivity().supportFragmentManager.popBackStack() // close this fragment
                    viewModel.mealAddedHandled()
                }
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // no op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        viewModel.setMealName(binding.mealName.text.toString())
        viewModel.setCaloriesCount(binding.calories.text.toString())
    }

    override fun afterTextChanged(p0: Editable?) {
        // no op
    }

    companion object {
        const val KEY_MEAL_ID = "MEAL_ID"
    }
}
