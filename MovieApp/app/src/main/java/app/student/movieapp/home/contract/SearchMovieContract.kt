package app.student.movieapp.home.contract

import app.student.movieapp.core.BaseMonitor
import app.student.movieapp.core.BaseMovieListerner
import app.student.movieapp.core.BasePresenter
import app.student.movieapp.core.BaseView
import app.student.movieapp.home.adapter.MovieSearchAdapter
import app.student.movieapp.home.storage.entity.SearchedMovies
import app.student.movieapp.model.Movie
import java.util.*

interface SearchMovieContract {


    // MovieListerner
    interface MovieSearchListener : BaseMovieListerner {
        fun listMoviesSearch(movieList: List<Movie>)
        fun detailMovieSearch(movie: Movie)
    }

    // MoviePresenter
    interface SearchMoviePresenter : BasePresenter{
        fun onGetSearchMovie(name: String)
        fun onPrepareMoviesView(name: String)
        fun onAddSearchedRecentBackGround(name: String, imgUrl: String, date: Date)
        fun onRemoveSearchRecentBackGround(name: String, date: Date)
        fun onGetSearchedRecentBackGround(): List<SearchedMovies>
        fun onSaveSearchedMovie(name: String, adapter: MovieSearchAdapter)
        fun onSearchMovieDataSetAdapter(name: String, list: List<Movie>): Movie?
        fun onPrepareSearchedMoviesView()

    }
    // View
    interface View : BaseView {
        fun onShowMoviesListSearched(movieList: List<Movie>)
        fun onShowSearchedMovies(movieList: List<SearchedMovies>)
        fun onActivateSearchListenerByQuery()

        fun onSetTextApresentationFragment(text: String)
    }

    // Monitor
    interface Monitor: BaseMonitor{

    }

}