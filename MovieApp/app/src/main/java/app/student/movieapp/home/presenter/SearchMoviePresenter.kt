package app.student.movieapp.home.presenter

import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.repository.SearchMovieRepository
import app.student.movieapp.model.Movie
import kotlin.reflect.KClass

class SearchMoviePresenter(private val view: SearchMovieContract.View,
private val repository: SearchMovieRepository, private val monitor: SearchMovieContract.Monitor) : SearchMovieContract.SearchMoviePresenter
,SearchMovieContract.MovieSearchListener{

    init {
        repository.attachListener(this)
    }

    override fun listMoviesSearch(movieList: List<Movie>) {

    }

    override fun detailMovieSearch(movie: Movie) {

    }

    override fun onGetSearchMovie(name: String) {
        repository.searchMovieByName(name,PAGE_INIT)
    }

    override fun onPrepareMoviesView() {

    }

    override fun <T : Any> onResponseOnErrorNetwork(clazz: KClass<T>) {

    }

    override fun <T> onResponseOnErrorNetwork(t: T) {
        when(t) {
            is NetworkFailure -> {
               view.onMessageError(t as NetworkFailure)
                monitor.trackNetworkFailure(t as NetworkFailure)
            }
            is Throwable -> {
                view.onMessageError(t as Throwable)
                monitor.trackNetworkError(NetworkError(t as Throwable))
            }
        }
    }

    override fun onDisposable() {
        repository.getDisposable().clear()
    }

    override fun onErrorSearch(t: Throwable) {
        onResponseOnErrorNetwork(t)
    }

    override fun onErrorSearch(f: NetworkFailure) {
        onResponseOnErrorNetwork(f)
    }

    companion object {
        private  var PAGE_INIT = 1
    }
}