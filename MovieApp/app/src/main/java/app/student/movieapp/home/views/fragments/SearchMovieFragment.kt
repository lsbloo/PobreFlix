package app.student.movieapp.home.views.fragments

import androidx.fragment.app.Fragment
import app.student.movieapp.R
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.model.Movie
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchMovieFragment : Fragment(R.layout.fragment_movie_search), SearchMovieContract.View {

    private val presenter: SearchMovieContract.SearchMoviePresenter by inject { parametersOf(this) }


    init {

    }

    override fun onShowMoviesListSearched(movieList: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun onMessageError(network: NetworkFailure) {
        TODO("Not yet implemented")
    }

    override fun onMessageError(t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDisposable()
    }
}