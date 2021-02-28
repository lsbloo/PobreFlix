package app.student.movieapp.home.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import app.student.movieapp.R
import app.student.movieapp.home.storage.entity.SearchedMovies
import com.bumptech.glide.Glide

class MovieSearchedAdapter(private val ctx: Context, private val searchList: List<SearchedMovies>): RecyclerView.Adapter<CustomViewHolderSearched>() {
    private val movieList: List<SearchedMovies> = searchList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderSearched {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_searched_item,parent,false)
        return DataViewHolderSearched(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolderSearched, position: Int) {
        holder.progressBar.visibility = View.GONE
        Glide.with(ctx)
            .load(BASE_URL_IMAGE + movieList[position].imgMovie)
            .into(holder.imageView)
        holder.textView.text = movieList[position].nameMovie
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}


class DataViewHolderSearched(itemView: View) : CustomViewHolderSearched(itemView)

open class CustomViewHolderSearched(itemView: View): RecyclerView.ViewHolder(itemView){
    val textView: TextView = itemView.findViewById<TextView>(R.id.txt_name_movie)
    val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageMovieSearch)
    val progressBar: ProgressBar = itemView.findViewById<ProgressBar>(R.id.progressBarSearch)
}