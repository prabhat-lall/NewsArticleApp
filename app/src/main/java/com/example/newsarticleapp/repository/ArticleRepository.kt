package com.example.newsarticleapp.repository

import com.example.newsarticleapp.model.Articles
import com.example.newsarticleapp.networking.RemoteAPI

class ArticleRepository {

    fun fetchArticleFromApi(): Articles {
        return RemoteAPI.makeGetRequest()
    }

}