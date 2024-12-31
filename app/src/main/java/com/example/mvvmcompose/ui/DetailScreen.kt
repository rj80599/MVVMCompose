package com.example.mvvmcompose.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmcompose.retrofit.Response
import com.example.mvvmcompose.viewmodel.TweetsViewModel

@Composable
fun DetailScreen() {
    val tweetsViewModel: TweetsViewModel = hiltViewModel()
    val uiState = tweetsViewModel.tweets.collectAsState()
    when (val value = uiState.value) {
        is Response.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Error: ${value.errorMessage}", color = Color.Red)
            }
        }

        is Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is Response.Success -> {
            LazyColumn {
                items(value.data!!) {
                    DetailItem(it.category, it.text)
                }
            }
        }
    }

}

@Composable
fun DetailItem(title: String, tweet: String) {
    Card(
        elevation = CardDefaults.cardElevation(
            4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(5))

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(title)
            Text(tweet)
        }
    }
}