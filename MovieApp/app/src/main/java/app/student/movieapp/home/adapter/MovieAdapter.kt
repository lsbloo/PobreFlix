package app.student.movieapp.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.student.movieapp.R
import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter(dataSet: List<Movie>, private val ctx: Context, private val listenerScrolled: MovieListContract.onScrolledAdapterListener
,private val recyclerView: RecyclerView,private val laoyoutScreenAdapter: Int) : RecyclerView.Adapter<CustomViewHolder>() {
    private val movieList = dataSet
    private var isScrolled = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        if(viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(laoyoutScreenAdapter, parent, false)
            return DataViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item_progressbar, parent, false)
        return ProgressViewHolder(view)

    }

    override fun getItemViewType(position: Int): Int {
        if (position == (itemCount-1)) {
            return VIEW_TYPE_LOADING;
        }
        return VIEW_TYPE_ITEM;
    }
    override fun getItemCount(): Int {
        return movieList.size
    }


    private fun bindMovieHolder(holder: DataViewHolder, position: Int){
        holder.textView.text = movieList[position].title
        Glide.with(ctx)
                .load(BASE_URL_IMAGE + movieList[position].backdrop_path)
                .into(holder.imageView);

        holder.progressBar.visibility = View.GONE
        if (position == (itemCount-1)) {
            scroledDown()
        } else {
            scroledUp()
        }
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if(holder is DataViewHolder){
            bindMovieHolder(holder,position)
        }else {
            scroledDown()
        }
    }

    private fun scroledUp() {
        isScrolled = false
    }

    private fun scroledDown()  {
        Handler().postDelayed({
            isScrolled = true
            listenerScrolled.onScrolledDown(isScrolled,recyclerView)
        }, TIME_UPDATE_DATA)
    }
    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
        private const val TIME_UPDATE_DATA: Long=2000
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1

    }
}

class ProgressViewHolder(itemView: View): CustomViewHolder(itemView)
class DataViewHolder(itemView: View) : CustomViewHolder(itemView)


open class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val textView = itemView.findViewById<TextView>(R.id.txt_name_movie)
    val imageView = itemView.findViewById<ImageView>(R.id.imageMovie)
    val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
}