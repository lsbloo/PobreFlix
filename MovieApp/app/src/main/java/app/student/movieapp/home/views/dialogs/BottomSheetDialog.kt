package app.student.movieapp.home.views.dialogs

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import app.student.movieapp.R
import app.student.movieapp.home.adapter.MovieSearchAdapter
import app.student.movieapp.model.Movie
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(movie: Movie, private val ctx: Context,layoutBottomSheet: Int) : BottomSheetDialogFragment() {

    private var bottomSheetPeekHeight: Int? = null
    private val movieSelected = movie
    private val layoutBottomSheet = layoutBottomSheet

    override fun getTheme() = R.style.Theme_MaterialComponents_Light_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(layoutBottomSheet, container, false)

        val bottomSheet = view.findViewById<ConstraintLayout>(R.id.constraintFather)
        bottomSheetPeekHeight =
            resources.getDimensionPixelSize(R.dimen.bottom_sheet_default_peek_height)

        val viewHolder = setUpHolder(view)
        setUpDataViewHolder(viewHolder)

        return view
    }

    private fun setUpDataViewHolder(holder: DataViewHolderDetailSearch) {
        holder.progressBar.visibility = View.GONE
        holder.title.text = movieSelected.title
        holder.overview.text = movieSelected.overview
        holder.year.text = getYearByReleaseData(movieSelected.releaseDate)
        Glide.with(ctx)
            .load(BASE_URL_IMAGE + movieSelected.backdrop_path)
            .into(holder.imageView)

        holder.imageButtonClosed.setOnClickListener {
            View.OnClickListener {
                System.err.println("clicked!")
            }
        }
    }

    private fun setUpHolder(view: View): DataViewHolderDetailSearch {
        val imageView = view.findViewById<ImageView>(R.id.imageMovie)
        val textView_title: TextView = view.findViewById<TextView>(R.id.title_movie)
        val textView_year: TextView = view.findViewById<TextView>(R.id.txt_year_movie)
        val textView_overView: TextView = view.findViewById<TextView>(R.id.txt_overview)
        val imageButton: ImageView = view.findViewById<ImageView>(R.id.img_closed)
        val buttonPlay: Button = view.findViewById<Button>(R.id.btn_play)
        val txtDownload: TextView = view.findViewById<TextView>(R.id.txt_download)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        return DataViewHolderDetailSearch(
            imageView,
            textView_title,
            textView_year,
            textView_overView,
            imageButton,
            buttonPlay,
            txtDownload,
            progressBar
        )
    }

    override fun onResume() {
        super.onResume()
        setUpBottomSheet()
    }

    private fun setUpBottomSheet() {
        val bottomSheetBehavior = BottomSheetBehavior.from(view?.parent as View)
        bottomSheetBehavior.peekHeight = bottomSheetPeekHeight!!
    }

    private fun getYearByReleaseData(data: String): String {
        return data.split("-")[0]
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}

data class DataViewHolderDetailSearch(
    val imageView: ImageView,
    val title: TextView,
    val year: TextView,
    val overview: TextView,
    val imageButtonClosed: ImageView,
    val btn_play: Button,
    val txtDownload: TextView,
    val progressBar: ProgressBar
)