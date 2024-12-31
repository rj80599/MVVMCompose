package com.example.mvvmcompose.repo

import android.util.Log
import com.example.mvvmcompose.model.Tweet
import com.example.mvvmcompose.retrofit.ApiInterface
import com.example.mvvmcompose.retrofit.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "MVVM"

@Singleton
class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    private val _tweets = MutableStateFlow<Response<List<Tweet>>>(Response.Loading())
    val tweets: StateFlow<Response<List<Tweet>>>
        get() = _tweets

    private val _categories = MutableStateFlow<Response<List<String>>>(Response.Loading())
    val categories: StateFlow<Response<List<String>>>
        get() = _categories

    suspend fun getCategories() {
        _categories.emit(Response.Loading())
        val response = apiInterface.getCategories()
        try {
            if (response.isSuccessful && response.body() != null) {
                _categories.emit(Response.Success(response.body()!!))
                Log.d(TAG, "getCategories: ${categories.value}")
            } else {
                _categories.emit(Response.Error(response.message()))
            }
        } catch (e: Exception) {
            _categories.emit(Response.Error(e.message))
        }
    }

    suspend fun getTweets(category: String) {
        _tweets.emit(Response.Loading())
        try {
            val result = apiInterface.getTweets("tweets[?(@.category==\"$category\")]")
            if (result.isSuccessful && result.body() != null) {
                println("tweet data  > " + result.body())
                _tweets.emit(Response.Success(result.body()!!))
            } else {
                _tweets.emit(Response.Error(result.message()))
            }
        } catch (e: Exception) {
            _tweets.emit(Response.Error(e.message))
        }
    }

}