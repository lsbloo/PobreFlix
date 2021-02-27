package app.student.movieapp.repository

import app.student.movieapp.core.BaseRepository
import app.student.movieapp.model.ListMovies
import io.reactivex.Observable
import retrofit2.Response

interface MovieListRepository: BaseRepository {



    fun getListMovies(page: Int)
    fun getListMoviesPopular(page: Int)
    fun disposableInstance()

}