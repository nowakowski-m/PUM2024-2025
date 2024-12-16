package com.example.lista3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.adapter.TaskAdapter
import com.example.lista3.model.Task

class E1Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private val tasks = listOf(
        Task("Lista 1", "Matematyka", "2024-12-20", "Do zrobienia"),
        Task("Lista 2", "Python", "2024-12-22", "W trakcie"),
        Task("Lista 3", "Fizyka", "2024-12-25", "Zakończone")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_e1, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_tasks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskAdapter = TaskAdapter(tasks) { task ->
            showTaskDetails(task)
        }

        recyclerView.adapter = taskAdapter

        return view
    }

    private fun showTaskDetails(task: Task) {
        val detailsFragment = E3Fragment.newInstance(task)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }
}
