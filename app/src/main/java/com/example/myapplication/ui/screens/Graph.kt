package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.viewmodel.GameViewModel

@Composable
fun Graph(){
    val gameViewModel: GameViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen) {

        composable(Routes.SplashScreen) {
            SplashScreen(navController)
        }
        composable(Routes.GameScreen) {
            GameScreen(navController,gameViewModel)
        }
        composable(Routes.ResultScreen) {
            ResultScreen(navController,gameViewModel)
        }

    }
}