package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import kotlin.random.Random
class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        setContent {
            RPS()
        }
    }
}
@Composable
fun RPS(){
    val choices = arrayOf("Taş","Kagıt","Makas")
    var userChoice by remember { mutableStateOf("") }
    var CompChoice by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }


    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = "taş-kağıt-makas")

        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            choices.forEach { choice ->
                Button(onClick = {userChoice=choice
                                    CompChoice=choices[Random.nextInt(3)]
                                    result = whoWin(userChoice,CompChoice)}) {
                    Text(text = choice)
                }
            }


        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(text="sen : $userChoice")
        Text(text="Bilgisayar : $CompChoice")

        Spacer(modifier = Modifier.height(40.dp))

        Text(text="sonuç  : $result")



    }
}

fun whoWin(user:String,comp:String):String{

    if(user==comp){return "berabere"}
    if(user=="Kağıt"){
        if(comp=="Taş"){return "kazandın"}
        else return "kaybettin"
    }
    if(user=="Makas"){
        if(comp=="Kağıt"){return "kazandın"}
        else return "kaybettin"
    }
    if(user=="Taş"){
        if(comp=="Makas"){return "kazandın"}
        else return "kaybettin"
    }
    return ""

}

