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

        // TODO - see this
        // When activity is recreated, addOnBackStackChangedListener callback is not called, so...
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribeUi()
        addBackStackListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
        }
    }

    private fun subscribeUi() {
        viewModel.state.observe(this) {
            it?.let {
                val newFragment = when (it.first) {
                    MainViewModel.State.ADD_EDIT -> {
                        val fragment = AddEditFragment()
                        val bundle = Bundle().apply {
                            // TODO - how to send null in a bundle?
                            putInt(AddEditFragment.KEY_MEAL_ID, it.second ?: 0)
                        }
                        fragment.arguments = bundle

                        fragment
                    }
                    MainViewModel.State.LIST -> ListFragment()
                    MainViewModel.State.VIEW -> {
                        val fragment = ViewDetailsFragment()
                        val bundle = Bundle().apply {
                            putInt(ViewDetailsFragment.KEY_MEAL_ID, it.second ?: 0)
                        }
                        fragment.arguments = bundle

                        fragment
                    }
                }

                supportFragmentManager.beginTransaction().apply {
                    replace(binding.fragment.id, newFragment)
                    if (newFragment !is ListFragment)
                        addToBackStack(null)
                    commit()
                }

                viewModel.stateHandled()
            }
        }
    }
}
