package app.student.movieapp.home.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SearchedMovies(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val nameMovie: String,
    val date: String
)
