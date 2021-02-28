package app.student.movieapp.home.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.student.movieapp.home.storage.entity.SearchedMovies


@Dao
interface SearchedMoviesDAO {

    @Insert
    fun add(searchedMovies: SearchedMovies)

    @Query("SELECT * FROM searchedmovies")
    fun getAll(): List<SearchedMovies>

    @Delete
    fun remove(searchedMovies: SearchedMovies)
}