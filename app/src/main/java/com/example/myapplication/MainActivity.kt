package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay



import kotlin.random.Random
data class Choice(
    val name: String,
    val image: Int
)

data class GameState(
    val round : Int = 1,
    val userScore : Int = 0,
    val computerScore: Int =0,
    val Finished:Boolean = false,
    val whoWin:String = ""
)

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            RpsApp()
        }
    }
}


@Composable
fun RpsApp(){
    var started by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(GameState()) }
    if(started){
        if(state.Finished){
            ResultScreen(state = state , onStateChanged = {newState -> state = GameState()})
        }
        else{
            RPS(state=state, onStateChanged = {newState->state=newState})
        }
    }
    else{
        SplashScreen(
            onClick = {started = true}
        )
    }

}
@Composable
fun SplashScreen(onClick: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2300)
        onClick()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {
        Text(
            text = "Uğur Talanın Yazdığı Taş Kağıt Makas uygulmasına hoşgeldiniz!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,

        )
    }

}

@Composable
fun RPS(state: GameState , onStateChanged : (GameState)-> Unit){

    val choices = arrayOf(Choice("Taş",R.drawable.rock),
        Choice("Kagıt",R.drawable.paper),
        Choice("Makas",R.drawable.scissors))
    var userChoice by remember { mutableStateOf<Choice?>(null) }
    var CompChoice by remember { mutableStateOf<Choice?>(null) }


    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Taş • Kağıt • Makas",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

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
                       val  newState = play(
                           state,choice,choices,compChoice
                           )
                        onStateChanged(newState)
                    }) {
                    Image(painter = painterResource(id = choice.image), contentDescription = choice.name, modifier = Modifier.fillMaxSize())
                }
            }


        }
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text= "Sen : ${userChoice?.name ?: "Seçim yapılmadı"}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text="Bilgisayar : ${CompChoice?.name ?: "Seçim yapılmadı"}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text="sonuç  : ${state.whoWin}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
                )
            Text(
                text="Sen  : ${state.userScore}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text="Bilgisayar  : ${state.computerScore}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(40.dp))





    }
}

@Preview(showBackground = true, name = "result screen")
@Composable
fun ResultScreen(state: GameState = GameState(), onStateChanged: (GameState) -> Unit = {}){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Computer : ${state.computerScore}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Sen : ${state.userScore}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "${state.whoWin} Kazandı!!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.width(70.dp).height(70.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray
            ),
            onClick = {onStateChanged(state)}) {
            Text(text = "Yeniden Oyna",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )
        }
    }


}


fun play(
    gameState : GameState ,userChoice: Choice  , choices : Array<Choice>,compChoice: Choice
): GameState{

    val result = whoWin(userChoice,compChoice)

    val newUserScore =if(result=="kazandın")   gameState.userScore+1 else gameState.userScore
    val newCompScore = if(result=="kaybettin") gameState.computerScore+1 else gameState.computerScore

    val roundCount = if(result!="berabere") gameState.round +1 else gameState.round
    var finished = roundCount>5

    var winner = ""

    if(finished || newUserScore == 3||newCompScore == 3){
        winner = if(newUserScore>newCompScore) "user" else "comp"
        finished=true
    }


    return GameState(
        round= roundCount,
        userScore = newUserScore,
        computerScore = newCompScore,
        Finished = finished,
        whoWin= winner
    )






}
fun whoWin(user:Choice,comp:Choice):String{
    if(user==comp){return "berabere"}
    if(user.name=="Kagıt"){
        if(comp.name=="Taş"){return "kazandın"}
        else return "kaybettin"
    }
    if(user.name=="Makas"){
        if(comp.name=="Kagıt"){return "kazandın"}
        else return "kaybettin"
    }
    if(user.name=="Taş"){
        if(comp.name=="Makas"){return "kazandın"}
        else return "kaybettin"
    }
    return ""

}

