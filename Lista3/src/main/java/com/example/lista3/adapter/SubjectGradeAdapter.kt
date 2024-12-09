package com.example.lista3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import com.example.lista3.model.SubjectGrade

class SubjectGradeAdapter(private val subjects: List<SubjectGrade>) :
    RecyclerView.Adapter<SubjectGradeAdapter.SubjectGradeViewHolder>() {

    class SubjectGradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectTextView: TextView = view.findViewById(R.id.subject_name)
        val gradeTextView: TextView = view.findViewById(R.id.subject_grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectGradeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject_grade, parent, false)
        return SubjectGradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectGradeViewHolder, position: Int) {
        val subjectGrade = subjects[position]
        holder.subjectTextView.text = subjectGrade.subject
        holder.gradeTextView.text = subjectGrade.grade.toString()
    }

    override fun getItemCount(): Int = subjects.size
}
