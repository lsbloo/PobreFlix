package app.student.movieapp.core.network

import app.student.movieapp.model.ListMovies
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIE_TOP)
    fun getMoviesTOP(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<ListMovies>>

    @GET(MOVIES_POPULAR)
    fun getMoviesPopular(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<ListMovies>>


    companion object {
        const val MOVIES_POPULAR = "movie/popular"
        const val MOVIE_TOP = "movie/top_rated"
        const val TOKEN_API = "141671642cb27ab3e1f4f58cf119fd6e"


    }
}