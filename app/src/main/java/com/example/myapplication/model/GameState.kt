package com.example.myapplication.model

data class GameState(
    val round : Int = 1,
    val userScore : Int = 0,
    val computerScore: Int =0,
    val Finished:Boolean = false,
    val whoWin:String = ""
)

