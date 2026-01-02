package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Choice
import com.example.myapplication.model.GameState
import com.example.myapplication.ui.screens.Graph
import com.example.myapplication.viewmodel.GameViewModel
import kotlinx.coroutines.delay



import kotlin.random.Random


class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            Graph()
        }
    }
}




fun play(gameViewModel: GameViewModel, userChoice: Choice, choices : Array<Choice>, compChoice: Choice){

    val result = whoWin(userChoice,compChoice)

    val newUserScore =if(result=="kazandın"&&!gameViewModel.gameState.value.Finished)   gameViewModel.gameState.value.userScore+1 else gameViewModel.gameState.value.userScore
    val newCompScore = if(result=="kaybettin"&&!gameViewModel.gameState.value.Finished) gameViewModel.gameState.value.computerScore+1 else gameViewModel.gameState.value.computerScore

    val roundCount = if(result!="berabere") gameViewModel.gameState.value.round +1 else gameViewModel.gameState.value.round



    var finished = roundCount>5

    var winner = ""

    if( newUserScore == 3||newCompScore == 3){
        winner = if(newUserScore>newCompScore) "user" else "comp"
        finished=true
    }
    gameViewModel.updateState(newUserScore,newCompScore,roundCount,winner)
    if(finished){

        gameViewModel.finish()
    }

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

