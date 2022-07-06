package com.adobe.calorie.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adobe.calorie.CalorieApp
import com.adobe.calorie.CustomFactory
import com.adobe.calorie.R
import com.adobe.calorie.databinding.FragmentListBinding
import com.adobe.calorie.result.Result

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, CustomFactory {
            ListViewModel(CalorieApp.mealsRepository)
        })[ListViewModel::class.java]

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setHasOptionsMenu(true)
        initRecyclerView()
        registerListeners()
        subscribeUi()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_screen, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_all -> viewModel.deleteAllMeals()
        }

        return true
    }

    private fun initRecyclerView() {
        binding.mealsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MealsAdapter {
                mainViewModel.viewDetails(it)
            }
        }
    }

    private fun subscribeUi() {
        viewModel.meals.observe(viewLifecycleOwner) {
            if (it is Result.Success)
                (binding.mealsList.adapter as MealsAdapter).submitList(it.data)
        }
    }

    private fun registerListeners() {
        binding.addMeal.setOnClickListener {
            mainViewModel.addNewMeal()
        }
    }
}
