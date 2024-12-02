package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.LoginBinding

class Login : Fragment() {

    private lateinit var binding: LoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginBinding.inflate(layoutInflater)

        binding.loginError.text = ""

        binding.buttonLogin.setOnClickListener {
            val userName = binding.loginUsername.text.toString()
            val userPassword = binding.loginPassword.text.toString()

            if (userName.isNotEmpty() && userPassword.isNotEmpty()) {

                val user = DataProvider.users.find { it.login == userName }

                if (user != null) {
                    if (user.password == userPassword) {
                        val action = LoginDirections.actionLoginToUserinterface()
                        Navigation.findNavController(requireView()).navigate(action)
                    } else {
                        binding.loginError.text = "Incorrect password."
                    }
                } else {
                    binding.loginError.text = "User not found."
                }

            } else {
                binding.loginError.text = "Fields can't be empty."
            }
        }
        return binding.root
    }
}
