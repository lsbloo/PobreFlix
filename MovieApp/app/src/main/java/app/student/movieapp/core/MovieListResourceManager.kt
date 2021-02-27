package app.student.movieapp.core

import android.content.Context
import app.student.movieapp.R
import app.student.movieapp.contract.MovieListContract

class MovieListResourceManager(private val ctx: Context): MovieListContract.ResourceManager{

    override fun getStringNetworkSuccessManager(): String {
        return ctx.getString(R.string.network_success_message)
    }

    override fun getStringNetworkSuccessOperationMovieListPopular(): String {
        return ctx.getString(R.string.network_success_operation_movie_list_popular)
    }

    override fun getStringNetworkSuccessOperationMovieListTop(): String {
        return ctx.getString(R.string.network_success_operation_movie_list_tops)
    }


}