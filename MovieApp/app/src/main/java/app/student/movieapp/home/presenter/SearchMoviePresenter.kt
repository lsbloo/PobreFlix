package app.student.movieapp.home.presenter

import app.student.movieapp.home.contract.SearchMovieContract
import kotlin.reflect.KClass

class SearchMoviePresenter() : SearchMovieContract.SearchMoviePresenter {


    override fun onGetSearchMovie(name: String) {
        TODO("Not yet implemented")
    }

    override fun onPrepareMoviesView() {
        TODO("Not yet implemented")
    }

    override fun <T : Any> onResponseOnErrorNetwork(clazz: KClass<T>) {
        TODO("Not yet implemented")
    }

    override fun <T> onResponseOnErrorNetwork(t: T) {
        TODO("Not yet implemented")
    }

    override fun onDisposable() {
        TODO("Not yet implemented")
    }

}