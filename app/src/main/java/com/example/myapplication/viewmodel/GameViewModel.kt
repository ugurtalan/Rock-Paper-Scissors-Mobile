package com.example.myapplication.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.GameState

class GameViewModel : ViewModel() {
    private val game_state = mutableStateOf(GameState())
    val gameState : State<GameState> = game_state

    fun updateState(userScore: Int,compScore : Int,round : Int,whoWin : String){
        game_state.value = game_state.value.copy(
            round=round,
            userScore = userScore,
            computerScore = compScore,
            whoWin = whoWin
        )
    }
    fun finish(){
        game_state.value = game_state.value.copy(
            Finished = true
        )
    }

    fun reset(){
        game_state.value = GameState()
    }
}