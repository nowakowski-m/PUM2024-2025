package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.ExerciseList
import com.example.myapplication.models.Subject
import com.example.myapplication.models.SubjectWithAverage

@Composable
fun GradesScreen(taskLists: List<ExerciseList>) {
    val subjectsWithAverageGrades = taskLists
        .groupBy { it.subject }
        .map { (subject, taskListsForSubject) ->
            println("Przedmiot: ${subject.name}, Liczba zadań: ${taskListsForSubject.size}")

            val averageGrade = taskListsForSubject.map { it.grade }.average()
            val listsCount = taskListsForSubject.size
            SubjectWithAverage(subject, averageGrade, listsCount)
        }

    if (taskLists.isEmpty()) {
        Text("Brak dostępnych danych")
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(subjectsWithAverageGrades) { subjectWithAverage ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = subjectWithAverage.subject.name,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Średnia ocen: ${"%.2f".format(subjectWithAverage.averageGrade)}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Liczba list: ${subjectWithAverage.listsCount}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}