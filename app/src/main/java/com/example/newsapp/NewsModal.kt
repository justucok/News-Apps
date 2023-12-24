package com.example.newsapp

data class NewsModal(
    var totalResult: Int = 0,
    var status: String? = null,
    var articles: ArrayList<Articles>? = null
)