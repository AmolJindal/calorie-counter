package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.adobe.calorie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        displayListFragment()
    }

    private fun displayListFragment() {
        // Don't add this transaction to back stack
        supportFragmentManager.beginTransaction().apply {
            add(ListFragment(), "LIST_FRAGMENT")
            commit()
        }
    }
}
