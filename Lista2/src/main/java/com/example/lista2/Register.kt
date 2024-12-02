package com.example.lista2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.RegisterBinding

class Register : Fragment() {

    private lateinit var binding: RegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterBinding.inflate(layoutInflater)

        binding.registerError.text = ""

        binding.buttonRegister.setOnClickListener {
            val userName = binding.registerUsername.text.toString()
            val userPassword = binding.registerPassword.text.toString()
            val userRepeated = binding.registerPasswordRepeat.text.toString()

            if (userName.isEmpty() || userPassword.isEmpty() || userRepeated.isEmpty()) {
                binding.registerError.text = "Fields can't be empty."
                return@setOnClickListener
            }

            if (userPassword != userRepeated) {
                binding.registerError.text = "Passwords don't match."
                return@setOnClickListener
            }

            else {
                DataProvider.users.add(User(userName, userPassword))
                val action = RegisterDirections.actionRegisterToLogin()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
        return binding.root
    }
}