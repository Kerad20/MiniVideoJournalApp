package com.example.videojournalapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.videojournalapp.domain.model.JournalEntry
import com.example.videojournalapp.presentation.viewmodels.FeedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedScreen(navController: NavController,
               vm: FeedViewModel = koinViewModel()
){

    val state = vm.state.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->

            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(
                    items = state.value.items,
                    key = { it.id }
                ){
                    FeedItem(it)
                }
            }
            // content
        }
}

@Composable
fun FeedItem(
    entry: JournalEntry
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            VideoPlayer(
                entry.videoPath
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = entry.description
            )
        }
    }
}