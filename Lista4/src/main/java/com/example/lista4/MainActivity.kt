package com.example.lista4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista4.ui.theme.Lista4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista4Theme {
                QuizScreen()
            }
        }
    }
}

val questions = listOf(
    Question(
        text = "Jakie jest największe jezioro w Polsce?",
        answers = listOf("Śniardwy", "Mamry", "Wigry", "Niegocin"),
        correctAnswerIndex = 0
    ),
    Question(
        text = "Które państwo ma największą powierzchnię na świecie?",
        answers = listOf("Chiny", "Kanada", "Rosja", "USA"),
        correctAnswerIndex = 2
    ),
    Question(
        text = "Jakie jest najwyższe pasmo górskie w Polsce?",
        answers = listOf("Bieszczady", "Tatry", "Sudety", "Gorce"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Jakie miasto jest nazywane Wiecznym Miastem?",
        answers = listOf("Ateny", "Rzym", "Jerozolima", "Paryż"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Który pierwiastek chemiczny ma symbol 'O'?",
        answers = listOf("Wodór", "Tlen", "Azot", "Hel"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Jaką planetę nazywamy Czerwoną Planetą?",
        answers = listOf("Mars", "Wenus", "Jowisz", "Saturn"),
        correctAnswerIndex = 0
    ),
    Question(
        text = "Który kontynent jest najmniejszy pod względem powierzchni?",
        answers = listOf("Europa", "Australia", "Antarktyda", "Ameryka Południowa"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Która rzeka jest najdłuższa na świecie?",
        answers = listOf("Nil", "Amazonka", "Jangcy", "Missisipi"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "W którym roku Polska wstąpiła do Unii Europejskiej?",
        answers = listOf("2002", "2004", "2006", "2008"),
        correctAnswerIndex = 1
    ),
    Question(
        text = "Który język programowania jest uważany za podstawę do tworzenia aplikacji na Androida?",
        answers = listOf("Python", "Swift", "Kotlin", "JavaScript"),
        correctAnswerIndex = 2
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    if (showResult) {
        ResultScreen(
            score = score,
            totalQuestions = questions.size,
            onRestart = {
                currentQuestionIndex = 0
                score = 0
                showResult = false
            }
        )
    } else {
        QuestionScreen(
            question = questions[currentQuestionIndex],
            onAnswerSelected = { isCorrect ->
                if (isCorrect) {
                    score++
                }
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                } else {
                    showResult = true
                }
            }
        )
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    onAnswerSelected: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            question.answers.forEachIndexed { index, answer ->
                Button(
                    onClick = { onAnswerSelected(index == question.correctAnswerIndex) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(answer)
                }
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Twój wynik: $score/$totalQuestions",
            fontSize = 24.sp
        )
        Button(
            onClick = { onRestart() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Spróbuj ponownie")
        }
    }
}

