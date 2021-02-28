package app.student.movieapp.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.student.movieapp.R
import app.student.movieapp.adapter.MovieAdapter
import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.BaseFragment
import app.student.movieapp.core.MovieListResourceManager
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.model.Movie
import app.student.movieapp.presenter.MovieListPresenter

import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ListMoviesFragment(context: Context) : BaseFragment(R.layout.fragment_movie_list), MovieListContract.View {

    private val presenter: MovieListPresenter by inject { parametersOf(this) }
    private val ctx: Context = context

    companion object {
        private const val PAGE_INIT_ = 1
    }

    init {
        presenter.attachResourceManager(MovieListResourceManager(ctx))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onMessageError(network: NetworkFailure) {
        Toast.makeText(ctx, "Não foi possivel encontrar os filmes", Toast.LENGTH_SHORT).show()
    }

    override fun onMessageError(t: Throwable) {
        Toast.makeText(ctx, "Não foi possivel encontrar os filmes", Toast.LENGTH_SHORT).show()
    }

    override fun onShowListMoviesTop(moviesList: List<Movie>) {
        stopSkeletonMoviesTop()
        recyclerViewTop.apply {
            adapter = MovieAdapter(moviesList, ctx,presenter,recyclerViewTop)
            layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    override fun onShowListMoviesPopular(moviesList: List<Movie>) {
        stopSkeletonMoviesPopular()
        recyclerViewPopular.apply {
            adapter = MovieAdapter(moviesList, ctx, presenter,recyclerViewPopular)
            layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onPrepareVisualizationTop(page: Int) {
        presenter.onPrepareVisualizationMoviesTop(page)
    }

    override fun onPrepareVisualizationPopular(page: Int) {
        presenter.onPrepareVisualizationMoviesPopular(page)
    }

    override fun stopSkeletonMoviesPopular() {
        skeletonAdapterPopular.stopShimmerAnimation()
        skeletonAdapterPopular.visibility = View.GONE
    }

    override fun stopSkeletonMoviesTop() {
        skeletonAdapterTop.stopShimmerAnimation()
        skeletonAdapterTop.visibility = View.GONE
    }

    override fun startSkeletonMoviesTop() {
        skeletonAdapterTop.visibility = View.VISIBLE
        skeletonAdapterTop.startShimmerAnimation()
    }

    override fun startSkeletonMoviesPopular() {
        skeletonAdapterPopular.visibility = View.VISIBLE
        skeletonAdapterPopular.startShimmerAnimation()
    }


    override fun onResume() {
        super.onResume()
        presenter.onPrepareVisualizationMoviesPopular(PAGE_INIT_)
        presenter.onPrepareVisualizationMoviesTop(PAGE_INIT_)

    }

    override fun onPause() {
        super.onPause()
        Log.d("OnPause", "OnPause ListMovieFragment Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDisposable()
        Log.d("OnDestroy", "OnDestroyer ListMovieFragment Called")
    }


}