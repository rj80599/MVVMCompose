package com.example.mvvmcompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmcompose.R
import com.example.mvvmcompose.retrofit.Response
import com.example.mvvmcompose.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    onClick: (cat: String) -> Unit
) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val uiState = categoryViewModel.categories.collectAsState()

    when (val value = uiState.value) {
        is Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is Response.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                items(value.data!!.distinct()) {
                    CategoryItem(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(160.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray)
                            .clickable {
                                onClick(it)
                            },
                        it
                    )
                }
            }
        }

        is Response.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Error: ${value.errorMessage}", color = Color.Red)
            }
        }
    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier, category: String) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.square_bg),
            contentDescription = ""
        )
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}