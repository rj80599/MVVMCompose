package com.example.mvvmcompose.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcompose.model.Tweet
import com.example.mvvmcompose.repo.Repository
import com.example.mvvmcompose.retrofit.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val tweets: StateFlow<Response<List<Tweet>>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("cat") ?: "web"
            repository.getTweets(category)
        }
    }
}