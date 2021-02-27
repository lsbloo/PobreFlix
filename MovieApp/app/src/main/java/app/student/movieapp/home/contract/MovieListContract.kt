package app.student.movieapp.contract

import androidx.recyclerview.widget.RecyclerView
import app.student.movieapp.adapter.MovieAdapter
import app.student.movieapp.core.BaseMonitor
import app.student.movieapp.core.BaseMovieListerner
import app.student.movieapp.core.BasePresenter
import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.model.NetworkSuccess
import app.student.movieapp.model.Movie

interface MovieListContract {

    // MovieAdapter
    interface onScrolledAdapterListener {
        fun onScrolledDown(flag: Boolean, recyclerView: RecyclerView)
    }


    // Presenter
    interface Presenter : BasePresenter {
        fun onGetListMoviesTop(page: Int)
        fun onGetListMoviesPopular(page: Int)
        fun onPrepareVisualizationMoviesTop(page: Int)
        fun onPrepareVisualizationMoviesPopular(page: Int)
    }

    // View
    interface View {
        fun onMessageError(network: NetworkFailure)
        fun onMessageError(t: Throwable)
        fun onShowListMoviesTop(moviesList: List<Movie>)
        fun onShowListMoviesPopular(moviesList: List<Movie>)
        fun onPrepareVisualizationTop(page: Int)
        fun onPrepareVisualizationPopular(page: Int)
        fun stopSkeletonMoviesPopular()
        fun stopSkeletonMoviesTop()
        fun startSkeletonMoviesTop()
        fun startSkeletonMoviesPopular()

    }

    interface MovieListListerner: BaseMovieListerner {
        fun listMoviesSearchPopular(moviesList: List<Movie>)
        fun listMoviesSearchTop(moviesList: List<Movie>)
    }

    interface Monitor: BaseMonitor{
    }

    interface ResourceManager{
        fun getStringNetworkSuccessManager(): String
        fun getStringNetworkSuccessOperationMovieListPopular(): String
        fun getStringNetworkSuccessOperationMovieListTop(): String
    }
}