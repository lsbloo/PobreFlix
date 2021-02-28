package app.student.movieapp.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.student.movieapp.R
import app.student.movieapp.adapter.MovieAdapter
import app.student.movieapp.model.Movie
import com.bumptech.glide.Glide
import java.io.FileNotFoundException

class MovieSearchAdapter(private val ctx: Context, private val dataSet: List<Movie>) :
    RecyclerView.Adapter<CustomViewHolder>() {
    private val movieList: ArrayList<Movie> = dataSet as ArrayList<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_search_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun bindDataSetHolder(holder: CustomViewHolder, position: Int) {

        holder.progressBar.visibility = View.GONE
        Glide.with(ctx)
            .load(BASE_URL_IMAGE + movieList[position].backdrop_path)
            .into(holder.imageView)


    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        bindDataSetHolder(holder = holder, position = position)
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}

class DataViewHolder(itemView: View) : CustomViewHolder(itemView)

open class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.findViewById<ImageView>(R.id.imageMovieSearch)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBarSearch)
}