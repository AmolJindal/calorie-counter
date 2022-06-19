package com.adobe.calorie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adobe.calorie.databinding.FragmentAddNewBinding

class AddNewFragment : Fragment() {

    lateinit var binding: FragmentAddNewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewBinding.inflate(inflater, container, false)
        registerListeners()
        return binding.root
    }

    private fun registerListeners() {

    }
}