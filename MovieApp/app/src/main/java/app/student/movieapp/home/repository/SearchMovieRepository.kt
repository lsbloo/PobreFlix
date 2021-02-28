package app.student.movieapp.home.repository

import app.student.movieapp.core.BaseRepository
import app.student.movieapp.home.storage.entity.SearchedMovies
import app.student.movieapp.model.Movie

interface SearchMovieRepository : BaseRepository{
    fun searchMovieByName(name: String, page: Int)
    fun getDetailsMovie(movie: Movie)
    fun addRecentSearchMoviesStorage(searchedMovies: SearchedMovies): Boolean
    fun getAllRecentSearchMoviesStorage(): List<SearchedMovies>
    fun deleteRecentSearchMoviesStorage(searchedMovies: SearchedMovies) : Boolean
}