package com.example.lista7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.lista7.model.Student
import com.example.lista7.ui.theme.Lista7Theme
import com.example.lista7.viewmodel.StudentsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista7Theme {
                val navController = rememberNavController()
                val studentsViewModel: StudentsViewModel = viewModel()

                AppNavigation(navController = navController, studentsViewModel = studentsViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, studentsViewModel: StudentsViewModel) {
    NavHost(
        navController = navController,
        startDestination = "students_list"
    ) {
        composable("students_list") {
            StudentsListScreen(
                students = studentsViewModel.students,
                onStudentClick = { student ->
                    navController.navigate("student_details/${student.indexNumber}")
                }
            )
        }
        composable(
            "student_details/{indexNumber}",
            arguments = listOf(navArgument("indexNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val indexNumber = backStackEntry.arguments?.getString("indexNumber")
            val student = studentsViewModel.students.find { it.indexNumber == indexNumber }
            if (student != null) {
                StudentDetailsScreen(student = student)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailsScreen(student: Student) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Szczegóły Studenta") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = "Nr indeksu: ${student.indexNumber}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Imię: ${student.firstName}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Nazwisko: ${student.lastName}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Średnia ocen: ${student.averageGrade}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Rok studiów: ${student.studyYear}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsListScreen(students: List<Student>, onStudentClick: (Student) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista Studentów") }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(students) { student ->
                StudentListItem(student = student, onClick = { onStudentClick(student) })
            }
        }
    }
}

@Composable
fun StudentListItem(student: Student, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = "Nr indeksu: ${student.indexNumber}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "${student.firstName} ${student.lastName}", style = MaterialTheme.typography.bodyLarge)
    }
}
