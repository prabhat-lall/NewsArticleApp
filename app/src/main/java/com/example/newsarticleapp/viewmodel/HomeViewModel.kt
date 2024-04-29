package com.example.newsarticleapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsarticleapp.model.Articles
import com.example.newsarticleapp.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private val apiResponse = MutableLiveData<Articles>()
    val articleList: LiveData<Articles> = apiResponse
    private val repository = ArticleRepository()

    fun callApi() {

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchArticleFromApi()
                withContext(Dispatchers.Main) {
                    apiResponse.value = response
                    Log.d("_prabhat", "callApi: $response")
                }
            } catch (e: Exception) {
                Log.d("_prabhat", "callApi exception: ${e.localizedMessage}")
            }
        }
    }
}