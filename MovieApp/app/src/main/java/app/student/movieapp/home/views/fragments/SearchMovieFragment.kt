package app.student.movieapp.home.views.fragments


import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.student.movieapp.R
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.home.adapter.MovieSearchAdapter
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.presenter.SearchMoviePresenter
import app.student.movieapp.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_search.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class SearchMovieFragment() : Fragment(R.layout.fragment_movie_search), SearchMovieContract.View {

    private val presenter: SearchMoviePresenter by inject { parametersOf(this) }

    override fun onResume() {
        super.onResume()
        onActivateSearchListenerByQuery()
    }



    override fun onShowMoviesListSearched(movieList: List<Movie>) {
        recyclerViewSearch.apply {
            adapter = activity?.baseContext?.let { MovieSearchAdapter(it, movieList) }
            layoutManager =
                GridLayoutManager(activity?.baseContext, 3)
        }
    }

    override fun onActivateSearchListenerByQuery() {
        searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText.isNullOrEmpty()){
                   Toast.makeText(activity?.baseContext,"Pesquise por algum filme!", Toast.LENGTH_SHORT).show()
                }else {
                    presenter.onPrepareMoviesView(newText)
                }
                return true
            }

        })
    }

    override fun onMessageError(network: NetworkFailure) {
        Toast.makeText(
            activity?.baseContext,
            "Não foi possivel encontrar os filmes",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onMessageError(t: Throwable) {
        Toast.makeText(
            activity?.baseContext,
            "Não foi possivel encontrar os filmes",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDisposable()
    }

}