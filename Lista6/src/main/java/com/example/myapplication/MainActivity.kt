package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.GradesScreen
import com.example.myapplication.DetailScreen
import com.example.myapplication.ui.theme.TaskListScreen
import com.example.myapplication.models.Exercise
import com.example.myapplication.models.ExerciseList
import com.example.myapplication.models.Subject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskLists = generateDummyData()

        setContent {
            val navController = rememberNavController()
            AppNavHost(navController = navController, taskLists = taskLists)
        }
    }
}

fun generateDummyData(): List<ExerciseList> {
    val subjects = listOf(
        Subject("Matematyka"),
        Subject("PUM"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
    )

    val grades = listOf(3.0, 3.5, 4.0, 4.5, 5.0)

    return List(20) {
        val exercises = List((1..10).random()) {
            Exercise(
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                points = (1..10).random()
            )
        }
        ExerciseList(
            exercises = exercises,
            subject = subjects.random(),
            grade = grades.random()
        )
    }
}

sealed class Screen(val route: String) {
    object TaskList : Screen("taskList")
    object Grades : Screen("grades")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(navController: NavHostController, taskLists: List<ExerciseList>) {
    var selectedScreenRoute by remember { mutableStateOf(Screen.TaskList.route) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Listy zadań") },
                    label = { Text("Listy zadań") },
                    selected = selectedScreenRoute == Screen.TaskList.route,
                    onClick = {
                        selectedScreenRoute = Screen.TaskList.route
                        navController.navigate(Screen.TaskList.route)
                    }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Oceny") },
                    label = { Text("Oceny") },
                    selected = selectedScreenRoute == Screen.Grades.route,
                    onClick = {
                        selectedScreenRoute = Screen.Grades.route
                        navController.navigate(Screen.Grades.route)
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.TaskList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.TaskList.route) {
                TaskListScreen(taskLists = taskLists) { selectedTask ->
                    navController.navigate("detail/${taskLists.indexOf(selectedTask)}")
                }
            }
            composable("detail/{taskIndex}") { backStackEntry ->
                val taskIndex = backStackEntry.arguments?.getString("taskIndex")?.toIntOrNull()
                val taskList = taskLists.getOrNull(taskIndex ?: 0)
                if (taskList != null) {
                    DetailScreen(taskList = taskList) {
                        navController.popBackStack()
                    }
                }
            }
            composable(Screen.Grades.route) {
                GradesScreen(taskLists = taskLists)
            }
        }
    }
}