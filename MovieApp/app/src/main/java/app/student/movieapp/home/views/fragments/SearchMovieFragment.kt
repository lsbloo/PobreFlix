package app.student.movieapp.home.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.student.movieapp.HomeActivity
import app.student.movieapp.R
import app.student.movieapp.core.BaseActivity
import app.student.movieapp.core.BaseFragment
import app.student.movieapp.core.managerFragments.BaseBackPressedImpl
import app.student.movieapp.core.managerFragments.BaseIOFragment
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.home.adapter.MovieSearchAdapter
import app.student.movieapp.home.adapter.MovieSearchedAdapter
import app.student.movieapp.home.adapter.listeners.RecyclerItemClickListener
import app.student.movieapp.home.adapter.listeners.SearchMovieListenerClickAdapter
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.presenter.SearchMoviePresenter
import app.student.movieapp.home.storage.entity.SearchedMovies
import app.student.movieapp.home.views.dialogs.BottomSheetDialog
import app.student.movieapp.model.Movie
import kotlinx.android.synthetic.main.fragment_movie_search.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class SearchMovieFragment(layoutBottomSheet: Int) : BaseFragment(R.layout.fragment_movie_search), SearchMovieContract.View {

    private val presenter: SearchMoviePresenter by inject { parametersOf(this) }
    private var thisadapter: MovieSearchAdapter? = null
    private val layoutBottomSheet = layoutBottomSheet
    private var queryx: String?=null

    override fun onResume() {
        super.onResume()
        onActivateSearchListenerByQuery()
    }

    companion object {
        private const val QUANTITY_LINES_GRID_ = 3
         const val SEARCH_TXT_VIEW ="SÉRIES E FILMES"
         const val SEARCHED_TXT_VIEW = "PRINCIPAIS BUSCAS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val act: BaseActivity = activity as HomeActivity
        act.setOnBackPressedListener(BaseBackPressedImpl(activity as FragmentActivity,this))
        return super.onCreateView(inflater, container, savedInstanceState)


    }

    override fun onShowMoviesListSearched(movieList: List<Movie>) {
        recyclerViewSearch.apply {
            adapter = activity?.baseContext?.let { MovieSearchAdapter(it, movieList) }
            layoutManager =
                GridLayoutManager(activity?.baseContext, QUANTITY_LINES_GRID_)
            thisadapter = adapter as MovieSearchAdapter
        }
        onListenerClickRecyclerViewSearchMovie()
    }


    override fun onShowSearchedMovies(movieList: List<SearchedMovies>) {
        recyclerViewSearch.apply {
            adapter = activity?.baseContext?.let { MovieSearchedAdapter(it, movieList) }
            layoutManager = LinearLayoutManager(activity?.baseContext!!)
        }
    }

    override fun onActivateSearchListenerByQuery() {
        presenter.onPrepareSearchedMoviesView()
        searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()) {
                    presenter.onPrepareMoviesView(newText)
                }
                return true
            }

        })
    }

    override fun onSetTextApresentationFragment(text: String) {
        txtMovies.text = text
    }

    override fun onListenerClickRecyclerViewSearchMovie() {
        recyclerViewSearch.addOnItemTouchListener(
            RecyclerItemClickListener(
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        System.err.println("click $position")
                       thisadapter?.let { presenter.onSaveSearchedMovie(it.getMovieAtPosition(position).title, it) }
                       thisadapter?.getMovieAtPosition(position)?.let { BottomSheetDialog(it,activity?.baseContext!!,layoutBottomSheet).show(fragmentManager!!,"bottom_sheet_dialog") }
                    }

                })
        )
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