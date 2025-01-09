package com.example.lista4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        index = 1,
        text = "Jakie jest największe jezioro w Polsce?",
        answers = listOf("Śniardwy", "Mamry", "Wigry", "Niegocin"),
        correctAnswerIndex = 0
    ),
    Question(
        index = 2,
        text = "Które państwo ma największą powierzchnię na świecie?",
        answers = listOf("Chiny", "Kanada", "Rosja", "USA"),
        correctAnswerIndex = 2
    ),
    Question(
        index = 3,
        text = "Jakie jest najwyższe pasmo górskie w Polsce?",
        answers = listOf("Bieszczady", "Tatry", "Sudety", "Gorce"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 4,
        text = "Jakie miasto jest nazywane Wiecznym Miastem?",
        answers = listOf("Ateny", "Rzym", "Jerozolima", "Paryż"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 5,
        text = "Który pierwiastek chemiczny ma symbol 'O'?",
        answers = listOf("Wodór", "Tlen", "Azot", "Hel"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 6,
        text = "Jaką planetę nazywamy Czerwoną Planetą?",
        answers = listOf("Mars", "Wenus", "Jowisz", "Saturn"),
        correctAnswerIndex = 0
    ),
    Question(
        index = 7,
        text = "Który kontynent jest najmniejszy pod względem powierzchni?",
        answers = listOf("Europa", "Australia", "Antarktyda", "Ameryka Południowa"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 8,
        text = "Która rzeka jest najdłuższa na świecie?",
        answers = listOf("Nil", "Amazonka", "Jangcy", "Missisipi"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 9,
        text = "W którym roku Polska wstąpiła do Unii Europejskiej?",
        answers = listOf("2002", "2004", "2006", "2008"),
        correctAnswerIndex = 1
    ),
    Question(
        index = 10,
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
    var selectedAnswerIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pytanie ${question.index} / 10",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )

        LinearProgressIndicator(
            progress = question.index / 10f,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = question.text,
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                question.answers.forEachIndexed { index, answer ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        RadioButton(
                            selected = selectedAnswerIndex == index,
                            onClick = { selectedAnswerIndex = index }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = answer)
                    }
                }
                Button(
                    onClick = {
                        if (selectedAnswerIndex != -1) {
                            onAnswerSelected(selectedAnswerIndex == question.correctAnswerIndex)
                            selectedAnswerIndex = -1 // Resetowanie zaznaczenia
                        }
                    },
                    enabled = selectedAnswerIndex != -1,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text("Zatwierdź odpowiedź")
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