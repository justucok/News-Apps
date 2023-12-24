package com.example.newsapp

import NewsRVAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CategoryRVAdapter.CategoryClickInterface {

//    4c32a628146841299f4f356e4276ba9a

    private lateinit var newsRV: RecyclerView
    private lateinit var categoryRV: RecyclerView
    private lateinit var loadingPB: ProgressBar
    private lateinit var articlesArrayList: ArrayList<Articles>
    private lateinit var categoryRVModalArrayList: ArrayList<CategoryRVModal>
    private lateinit var categoryRVAdapter: CategoryRVAdapter
    private lateinit var newsRVAdapter: NewsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi
        newsRV = findViewById(R.id.idRVNews)
        categoryRV = findViewById(R.id.idRVCategories)
        loadingPB = findViewById(R.id.idPBLoading)
        articlesArrayList = ArrayList()
        categoryRVModalArrayList = ArrayList()
        newsRVAdapter = NewsRVAdapter(articlesArrayList, this)
        categoryRVAdapter = CategoryRVAdapter(categoryRVModalArrayList, this, this)
        newsRV.layoutManager = LinearLayoutManager(this)
        newsRV.adapter = newsRVAdapter
        categoryRV.adapter = categoryRVAdapter
        getCategories()
        getNews(category)
        newsRVAdapter.notifyDataSetChanged()

        // Lakukan hal lain sesuai kebutuhan aplikasi Anda
    }

    private var category: String = "All" // Deklarasi dan inisialisasi variabel category

    private fun getCategories() {
        categoryRVModalArrayList.add(CategoryRVModal("All", "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8bmV3c3xlbnwwfHwwfHx8MA%3D%3D"))
        categoryRVModalArrayList.add(CategoryRVModal("Technology", "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D"))
        categoryRVModalArrayList.add(CategoryRVModal("Sport", "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnR8ZW58MHx8MHx8fDA%3D"))
        categoryRVModalArrayList.add(CategoryRVModal("Entertainment", "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHx8MA%3D%3D"))
        categoryRVAdapter.notifyDataSetChanged()

        // Panggil getNews() setelah menambahkan kategori
//        getNews()
    }

    private fun getNews(category: String?) {
        loadingPB.visibility = View.VISIBLE
        articlesArrayList.clear()

        val categoryUrl =
            "https://newsapi.org/v2/top-headlines?country=id&category=${this.category}&apiKey=4c32a628146841299f4f356e4276ba9a"
        val url =
            "https://newsapi.org/v2/top-headlines?country=id&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=id&apiKey=4c32a628146841299f4f356e4276ba9a"
        val BASE_URL = "https://newsapi.org/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<NewsModal> =
            if (this.category == "All") {
                retrofitAPI.getAllNews(url)
            } else {
                retrofitAPI.getNewsByCategory(categoryUrl)
            }

        // Lanjutkan dengan melanjutkan panggilan API dan menangani responsnya
        call.enqueue(object : Callback<NewsModal> {
            override fun onResponse(call: Call<NewsModal>, response: Response<NewsModal>) {
//                loadingPB.visibility = View.GONE

                val newsModal = response.body()
                loadingPB.visibility = View.GONE
                val articles = newsModal?.articles
                articles?.let {
                    for (i in 0 until it.size) {
                        articlesArrayList.add(
                            Articles(
                                it[i].title,
                                it[i].description,
                                it[i].urlToImage,
                                it[i].url,
                                it[i].content
                            )
                        )
                    }
                    newsRVAdapter.notifyDataSetChanged()
                }

//                if (response.isSuccessful) {
//                    val newsModal = response.body()
//                    if (newsModal != null) {
//                        newsModal.articles?.let { articlesArrayList.addAll(it) }
//                        newsRVAdapter.notifyDataSetChanged()
//                    }
//                } else {
//                    // Tangani kesalahan respons di sini
//                    Log.e("getNews", "Response error: ${response.code()}")
//                }
            }

            override fun onFailure(call: Call<NewsModal>, t: Throwable) {
                // loadingPB.visibility = View.GONE // Jangan lupa matikan loadingPB jika diperlukan
                // Tangani kesalahan jaringan di sini
                Toast.makeText(this@MainActivity, "Fail to get news", Toast.LENGTH_SHORT).show()
                Log.e("getNews", "Response error: ${t.message}")
            }

        })
    }




    override fun onCategoryClick(position: Int) {
        val category = categoryRVModalArrayList[position].category
        getNews(category)
    }

}