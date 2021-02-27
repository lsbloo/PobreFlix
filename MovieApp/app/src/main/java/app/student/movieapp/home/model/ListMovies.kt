package app.student.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMovies(
    @SerializedName("results")
    @Expose
    val listMovies: List<Movie>
)