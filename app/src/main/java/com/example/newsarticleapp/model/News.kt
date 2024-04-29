package com.example.newsarticleapp.model

import java.io.Serializable

data class News(
    val headLine:String,
    val description:String,
    val imageUrl: String,
    val url: String
) : Serializable
