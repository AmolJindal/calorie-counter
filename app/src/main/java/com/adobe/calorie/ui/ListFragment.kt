package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adobe.calorie.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        initRecyclerView()
        subscribeUi()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.mealsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MealsAdapter()
        }
    }

    private fun subscribeUi() {
        viewModel.meals.observe(viewLifecycleOwner) {
            (binding.mealsList.adapter as MealsAdapter).submitList(it)
        }
    }
}
