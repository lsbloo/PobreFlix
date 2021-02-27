package app.student.movieapp.repository

import app.student.movieapp.model.ListMovies
import io.reactivex.Observable
import retrofit2.Response

interface MovieListRepository {



    fun getListMovies(page: Int)
    fun getListMoviesPopular(page: Int)
    fun disposableInstance()

}