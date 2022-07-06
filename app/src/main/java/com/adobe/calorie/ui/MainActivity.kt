package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adobe.calorie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.state.observe(this) {
            val newFragment = when (it.first) {
                MainViewModel.State.ADD_EDIT -> AddNewFragment()
                MainViewModel.State.LIST -> ListFragment()
                MainViewModel.State.VIEW -> {
                    val fragment = ViewDetailsFragment()
                    val bundle = Bundle().apply {
                        putInt(ViewDetailsFragment.KEY_MEAL_ID, it.second?.id ?: 0)
                    }
                    fragment.arguments = bundle

                    fragment
                }
            }

            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragment.id, newFragment, "ADD_NEW_FRAGMENT")
                if (newFragment !is ListFragment)
                    addToBackStack(null)
                commit()
            }
        }
    }
}
