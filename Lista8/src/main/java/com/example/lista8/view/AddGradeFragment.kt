package com.example.lista8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lista8.R
import com.example.lista8.viewmodel.GradesViewModel
import com.example.lista8.model.Grade

class AddGradeFragment : Fragment() {

    private lateinit var gradesViewModel: GradesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_grade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gradesViewModel = ViewModelProvider(this)[GradesViewModel::class.java]

        val subjectNameInput = view.findViewById<EditText>(R.id.subjectNameInput)
        val gradeInput = view.findViewById<EditText>(R.id.gradeInput)
        val buttonSave = view.findViewById<View>(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val subjectName = subjectNameInput.text.toString()
            val grade = gradeInput.text.toString().toDoubleOrNull()

            if (subjectName.isNotEmpty() && grade != null) {
                val newGrade = Grade(0, subjectName, grade)
                gradesViewModel.addGrade(newGrade)
                Toast.makeText(context, "Ocena zapisana!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Wypełnij poprawnie wszystkie pola!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
