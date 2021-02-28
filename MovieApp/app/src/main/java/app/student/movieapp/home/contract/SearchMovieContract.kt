package app.student.movieapp.home.contract

import app.student.movieapp.core.BaseMonitor
import app.student.movieapp.core.BaseMovieListerner
import app.student.movieapp.core.BasePresenter
import app.student.movieapp.core.BaseView
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
        fun onAddSearchedRecentBackGround(name: String, date: Date)
        fun onRemoveSearchRecentBackGround(name: String, date: Date)
        fun onGetSearchedRecentBackGround()

    }
    // View
    interface View : BaseView {
        fun onShowMoviesListSearched(movieList: List<Movie>)
        fun onActivateSearchListenerByQuery()
    }

    // Monitor
    interface Monitor: BaseMonitor{

    }

}