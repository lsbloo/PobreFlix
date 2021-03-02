package app.student.movieapp.home.presenter

import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.home.adapter.MovieSearchAdapter
import app.student.movieapp.home.extensions.formatDateBr
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.monitoring.SearchMovieMonitoring
import app.student.movieapp.home.repository.SearchMovieRepositoryImpl
import app.student.movieapp.home.storage.entity.SearchedMovies
import app.student.movieapp.home.views.fragments.SearchMovieFragment
import app.student.movieapp.model.Movie
import java.util.*
import kotlin.reflect.KClass

class SearchMoviePresenter(private val view: SearchMovieContract.View, private val repository: SearchMovieRepositoryImpl, private val monitor: SearchMovieMonitoring) : SearchMovieContract.SearchMoviePresenter
,SearchMovieContract.MovieSearchListener{

    init {
        repository.attachListener(this)
    }

    private fun getDate() = Date()

    override fun listMoviesSearch(movieList: List<Movie>) {

        // ->> Falta implementar o Skeleton !
        view.onSetTextApresentationFragment(SearchMovieFragment.SEARCH_TXT_VIEW)
        view.onShowMoviesListSearched(movieList)
    }

    override fun detailMovieSearch(movie: Movie) {

    }

    override fun onGetSearchMovie(name: String) {
        repository.searchMovieByName(name,PAGE_INIT)
    }

    override fun onPrepareMoviesView(name: String) {
        onGetSearchMovie(name)
    }

    override fun onAddSearchedRecentBackGround(name: String, imgUrl: String,date: Date) {
      repository.addRecentSearchMoviesStorage(SearchedMovies(nameMovie = name,date = date.formatDateBr(), imgMovie = imgUrl))
    }

    // Implementar logica para remoção de mais antigos! Definir um time
    override fun onRemoveSearchRecentBackGround(name: String, date: Date) {

    }

    override fun onGetSearchedRecentBackGround(): List<SearchedMovies> {
        return repository.getAllRecentSearchMoviesStorage()
    }

    override fun onSaveSearchedMovie(name: String, adapter: MovieSearchAdapter) {
        val dataset = adapter.getDataSet()
        val movie = onSearchMovieDataSetAdapter(name,dataset)
        if(movie!=null){
            onAddSearchedRecentBackGround(movie.title,movie.backdrop_path,getDate())
        }
    }

    override fun onSearchMovieDataSetAdapter(name: String, list: List<Movie>): Movie? {
        val result = list.filter { movie -> movie.title.toLowerCase().contains(name) }
            .let { t -> if (t.isNotEmpty()) {
                    return t[0] }
            }
        return null
    }

    override fun onPrepareSearchedMoviesView() {
        view.onSetTextApresentationFragment(SearchMovieFragment.SEARCHED_TXT_VIEW)
        view.onShowSearchedMovies(onGetSearchedRecentBackGround())
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