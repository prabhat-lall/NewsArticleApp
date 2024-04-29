package com.example.newsarticleapp.networking

import com.example.newsarticleapp.model.Articles
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object RemoteAPI {
    private const val BASE_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    fun makeGetRequest(): Articles {
        val url = URL(BASE_URL)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line?.trim())
            }

            reader.close()
            inputStream.close()
            connection.disconnect()
            return Gson().fromJson(response.toString(),Articles::class.java)
        } else {
            throw Exception("Failed to make GET request. Response code: $responseCode")
        }
    }

}