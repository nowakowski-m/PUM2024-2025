package com.example.lista3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lista3.model.Task

class E3Fragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var deadlineTextView: TextView
    private lateinit var statusTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_e3, container, false)

        titleTextView = view.findViewById(R.id.task_title)
        descriptionTextView = view.findViewById(R.id.task_description)
        deadlineTextView = view.findViewById(R.id.task_deadline)
        statusTextView = view.findViewById(R.id.task_status)

        val task = arguments?.getParcelable<Task>("task")

        task?.let {
            titleTextView.text = it.title
            descriptionTextView.text = it.description
            deadlineTextView.text = "Termin: ${it.deadline}"
            statusTextView.text = "Status: ${it.status}"
        }

        return view
    }

    companion object {
        fun newInstance(task: Task): E3Fragment {
            val fragment = E3Fragment()
            val bundle = Bundle()
            bundle.putParcelable("task", task)
            fragment.arguments = bundle
            return fragment
        }
    }
}
