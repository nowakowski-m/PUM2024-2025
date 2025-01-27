package com.example.lista8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista8.R
import com.example.lista8.viewmodel.GradesViewModel

class GradesListFragment : Fragment() {

    private lateinit var gradesViewModel: GradesViewModel
    private lateinit var gradesAdapter: GradesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grades_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gradesViewModel = ViewModelProvider(this).get(GradesViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGrades)
        recyclerView.layoutManager = LinearLayoutManager(context)

        gradesViewModel.allGrades.observe(viewLifecycleOwner, Observer { grades ->
            gradesAdapter = GradesAdapter(grades)
            recyclerView.adapter = gradesAdapter
        })

        val buttonNew = view.findViewById<View>(R.id.buttonNew)
        buttonNew.setOnClickListener {
            findNavController().navigate(R.id.action_gradesListFragment_to_addGradeFragment)
        }
    }
}
