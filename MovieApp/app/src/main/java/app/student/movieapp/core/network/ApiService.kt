package app.student.movieapp.core.network

import app.student.movieapp.core.network.ApiService.Companion.DETAIL_MOVIE
import app.student.movieapp.core.network.ApiService.Companion.SEARCH_MOVIE
import app.student.movieapp.model.ListMovies
import app.student.movieapp.model.Movie
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(MOVIE_TOP)
    fun getMoviesTOP(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<ListMovies>>

    @GET(MOVIES_POPULAR)
    fun getMoviesPopular(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<ListMovies>>

    @GET(SEARCH_MOVIE)
    fun getMoviesBySearchName(@Query("query") name: String, @Query("api_key") api_key: String, @Query("page") page: Int, @Query("language") language: String = "pt-br"): Observable<Response<ListMovies>>

    @GET(DETAIL_MOVIE)
    fun getDetaisMovie(@Query("api_key") api_key: String, @Query("movie_id") movie_id: Int, @Query("language") language: String = "pt-br"): Observable<Response<Movie>>

    companion object {
        private const val MOVIES_POPULAR = "movie/popular"
        private const val MOVIE_TOP = "movie/top_rated"
        private const val SEARCH_MOVIE ="search/movie"
        private const val DETAIL_MOVIE = "movie/"
        const val TOKEN_API = "141671642cb27ab3e1f4f58cf119fd6e"

    }
}