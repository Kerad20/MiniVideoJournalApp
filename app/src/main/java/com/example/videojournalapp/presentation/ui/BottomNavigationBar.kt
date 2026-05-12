package com.example.videojournalapp.presentation.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.videojournalapp.domain.model.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val items = listOf(
        BottomNavItem(
            "record",
            "Record",
            Icons.Default.Videocam
        ),
        BottomNavItem(
            "feed",
            "Video Feed",
            Icons.Default.VideoFile
        )
    )

    NavigationBar {

        items.forEach { screen ->

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = { Icon(screen.icon, null) },
                label = { Text(screen.label) }
            )
        }
    }
}