package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.Choice
import com.example.myapplication.play
import com.example.myapplication.viewmodel.GameViewModel
import kotlin.random.Random

@Composable
fun GameScreen(navController: NavController,gameViewModel: GameViewModel){

    val choices = arrayOf(
        Choice("Taş", R.drawable.rock),
        Choice("Kagıt",R.drawable.paper),
        Choice("Makas",R.drawable.scissors))
    var userChoice by remember { mutableStateOf<Choice?>(null) }
    var CompChoice by remember { mutableStateOf<Choice?>(null) }


    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Taş • Kağıt • Makas",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            choices.forEach { choice ->
                Button(
                    modifier = Modifier.width(60.dp).height(60.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    onClick = {userChoice=choice
                        val compChoice  = choices[Random.nextInt(3)]
                        CompChoice = compChoice
                        play(gameViewModel,choice,choices,compChoice)
                        if (gameViewModel.gameState.value.Finished){
                            navController.navigate(Routes.ResultScreen)
                        }
                    }) {
                    Image(painter = painterResource(id = choice.image), contentDescription = choice.name, modifier = Modifier.fillMaxSize())
                }
            }


        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text= "Sen : ${userChoice?.name ?: "Seçim yapılmadı"}",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text= "Bilgisayar : ${CompChoice?.name ?: "Seçim yapılmadı"}",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }



            Card(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text= "Sen : ${gameViewModel.gameState.value.userScore}",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }



            Card(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text= "Sen : ${gameViewModel.gameState.value.computerScore}",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))





    }
}