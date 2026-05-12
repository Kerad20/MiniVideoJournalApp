package com.example.videojournalapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.videojournalapp.Routes

@Composable
fun Navigation(){

    val navHostController = rememberNavController()

    NavHost(navHostController, startDestination = Routes.record) {
        composable (Routes.record) {
            RecordScreen(navHostController)
        }

        composable (Routes.feed) {
            FeedScreen(navHostController)
        }
    }
}