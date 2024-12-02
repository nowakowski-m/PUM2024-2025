package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.WelcomeBinding

class Welcome : Fragment() {

    private lateinit var binding: WelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WelcomeBinding.inflate(layoutInflater)
        binding.buttonWelcomeLogin.setOnClickListener {
            val action = WelcomeDirections.actionWelcomeToLogin()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.buttonWelcomeRegister.setOnClickListener {
            val action = WelcomeDirections.actionWelcomeToRegister()
            Navigation.findNavController(requireView()).navigate(action)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}