package com.example.lab6_geoquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab6_geoquiz.ui.theme.Lab6_GeoQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab6_GeoQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeoQuiz(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Question(
    val quest: String,
    val answer: Boolean
)

@Composable
fun GeoQuiz(modifier: Modifier = Modifier) {
    val questions = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )
    var round by remember { mutableStateOf(0) }
    var correct by remember { mutableStateOf(0) }
    var canGetAnswer by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = questions[round].quest,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            if (canGetAnswer) {
                Row(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(onClick = {
                        if (!questions[round].answer) {
                            correct++
                        }

                        canGetAnswer = false
                    }) {
                        Text("True")
                    }

                    Button(onClick = {
                        if (questions[round].answer) {
                            correct++
                        }

                        canGetAnswer = false
                    }) {
                        Text("False")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab6_GeoQuizTheme {
        GeoQuiz()
    }
}