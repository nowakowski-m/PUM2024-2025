package com.example.lista8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista8.R
import com.example.lista8.model.Grade

class GradesAdapter(private val grades: List<Grade>) : RecyclerView.Adapter<GradesAdapter.GradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grade, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val grade = grades[position]
        holder.subjectTextView.text = grade.subject
        holder.gradeTextView.text = grade.grade.toString()
    }

    override fun getItemCount(): Int = grades.size

    class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(R.id.textViewSubject)
        val gradeTextView: TextView = itemView.findViewById(R.id.textViewGrade)
    }
}
