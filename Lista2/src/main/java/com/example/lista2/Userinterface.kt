package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.UserinterfaceBinding

class Userinterface : Fragment() {

    private lateinit var binding: UserinterfaceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserinterfaceBinding.inflate(layoutInflater)
        binding.buttonUserinterface.setOnClickListener {
            val action = UserinterfaceDirections.actionUserinterfaceToWelcome()
            Navigation.findNavController(requireView()).navigate(action)
        }
        return binding.root
    }
}