package com.example.mvvmcompose.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmcompose.viewmodel.TweetsViewModel

@Composable
fun DetailScreen() {
    val tweetsViewModel: TweetsViewModel = hiltViewModel()
    val tweets = tweetsViewModel.tweets.collectAsState()

    LazyColumn {
        items(tweets.value) {
            DetailItem(it.category, it.text)
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