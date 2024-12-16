package com.example.lista3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.adapter.SubjectGradeAdapter
import com.example.lista3.model.SubjectGrade

class E2Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubjectGradeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_e2, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_subjects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val subjects = listOf(
            SubjectGrade("Matematyka", 4.5f),
            SubjectGrade("Fizyka", 3.0f),
            SubjectGrade("Informatyka", 5.0f),
            SubjectGrade("Biologia", 4.0f)
        )

        adapter = SubjectGradeAdapter(subjects)
        recyclerView.adapter = adapter

        val averageGrade = subjects.map { it.grade }.average()
        val averageTextView: TextView = view.findViewById(R.id.average_text_view)
        averageTextView.text = "Średnia ocen: %.2f".format(averageGrade)

        return view
    }
}
