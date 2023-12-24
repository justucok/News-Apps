//package com.example.newsapp
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.squareup.picasso.Picasso
//
//class CategoryRVAdapter(private val context: ArrayList<CategoryRVModal>, private val categoryRVModal: MainActivity, private val categoryClickInterface: CategoryClickInterface,) :
//    RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(context).inflate(R.layout.categories_rv_item, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val categoryRVModal = categoryRVModal[position]
//        holder.categoryTv.text = categoryRVModal.category
//        Picasso.get().load(categoryRVModal.categoryImageUrl).into(holder.categoryIV)
//        holder.itemView.setOnClickListener {
//            categoryClickInterface.onCategoryClick(position)
//        }
//
//        // Implementasikan logika binding data ke tampilan lainnya di sini
//    }
//
//    override fun getItemCount(): Int {
//        return categoryRVModal.size
//    }
//
//    interface CategoryClickInterface {
//        fun onCategoryClick(position: Int)
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val categoryTv: TextView = itemView.findViewById(R.id.idTVCategory)
//        val categoryIV: ImageView = itemView.findViewById(R.id.idIVCategory)
//        // Deklarasikan tampilan lainnya di sini menggunakan itemView.findViewById
//    }
//
//}
//

package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CategoryRVAdapter(
    private val context: ArrayList<CategoryRVModal>,
    private val categoryClickInterface: CategoryClickInterface,
    mainActivity: MainActivity
) :
    RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoryRVModal = context[position]
        holder.categoryTv.text = categoryRVModal.category
        Picasso.get().load(categoryRVModal.categoryImageUrl).into(holder.categoryIV)
        holder.itemView.setOnClickListener {
            categoryClickInterface.onCategoryClick(position)
        }

        // Implementasikan logika binding data ke tampilan lainnya di sini
    }

    override fun getItemCount(): Int {
        return context.size
    }

    interface CategoryClickInterface {
        fun onCategoryClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTv: TextView = itemView.findViewById(R.id.idTVCategory)
        val categoryIV: ImageView = itemView.findViewById(R.id.idIVCategory)
        // Deklarasikan tampilan lainnya di sini menggunakan itemView.findViewById
    }
}

