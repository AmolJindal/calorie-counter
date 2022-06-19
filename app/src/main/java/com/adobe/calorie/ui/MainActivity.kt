package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.adobe.calorie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        displayListFragment()
        registerListeners()
    }

    private fun displayListFragment() {
        // Don't add this transaction to back stack
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragment.id, ListFragment(), "LIST_FRAGMENT")
            commit()
        }
    }

    private fun registerListeners() {
        binding.addMeal.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragment.id, AddNewFragment(), "ADD_NEW_FRAGMENT")
                addToBackStack(null)
                commit()
            }
        }
    }
}
