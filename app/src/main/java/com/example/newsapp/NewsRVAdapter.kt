import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Articles
import com.example.newsapp.NewsDetailActivity
import com.example.newsapp.R
import com.squareup.picasso.Picasso

class NewsRVAdapter(private val articlesArrayList: ArrayList<Articles>, private val context: Context) :
    RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.news_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Implementasikan logika binding data ke tampilan di sini
        val articles = articlesArrayList[position]
        holder.subTitleTV.text = articles.description
        holder.titleTV.text = articles.title
        Picasso.get().load(articles.urlToImage).into(holder.newsIV)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", articles.title)
            intent.putExtra("content", articles.content)
            intent.putExtra("desc", articles.description)
            intent.putExtra("image", articles.urlToImage)
            intent.putExtra("url", articles.url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        // Return jumlah item dalam dataset
        return articlesArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Deklarasikan tampilan di sini menggunakan itemView.findViewById
        val titleTV: TextView = itemView.findViewById(R.id.idTVNewsHeading)
        val subTitleTV: TextView = itemView.findViewById(R.id.idTVSubTitle)
        val newsIV: ImageView = itemView.findViewById(R.id.idIVNews)
    }
}
