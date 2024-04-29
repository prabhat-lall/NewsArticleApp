package com.example.newsarticleapp.model

import com.example.newsarticleapp.model.Article

data class Articles(
    val articles: List<Article>,
    val status: String
)