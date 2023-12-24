package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NewsDetailActivity : AppCompatActivity() {

    private var title: String? = null
    private var desc: String? = null
    private var content: String? = null
    private var imageUrl: String? = null
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        title = intent.getStringExtra("title")
        desc = intent.getStringExtra("desc")
        content = intent.getStringExtra("content")
        imageUrl = intent.getStringExtra("imageUrl")
        url = intent.getStringExtra("url")

        // Lakukan sesuatu dengan data yang diterima, misalnya menampilkan dalam tampilan
    }
}