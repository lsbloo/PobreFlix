package app.student.movieapp.repository

import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.network.ApiService
import app.student.movieapp.core.network.RetrofitInitializer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieListRepositoryImpl(): MovieListRepository {
    private val retrofit = RetrofitInitializer()
    private val disposable = CompositeDisposable()
    private var listener: MovieListContract.MovieListListerner? = null


     private fun getDisposable() = disposable

    fun attachListener(listenerx: MovieListContract.MovieListListerner){
        listener = listenerx
    }


    override fun getListMovies(page: Int) {
        getDisposable().add(retrofit.apiServiceMovie().getMoviesTOP(ApiService.TOKEN_API,page).
        observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({response -> response.body()?.listMovies?.let { listener?.listMoviesSearchTop(it)
                        if(!response.isSuccessful) listener?.onErrorSearch(NetworkFailure(response.message(),response.body().toString(),
                        response.code())) }}, { t -> listener?.onErrorSearch(t)}))

    }

    override fun getListMoviesPopular(page: Int) {
        getDisposable().add(retrofit.apiServiceMovie().getMoviesPopular(ApiService.TOKEN_API,page).
        observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({response -> response.body()?.listMovies?.let { listener?.listMoviesSearchPopular(it)
                        if(!response.isSuccessful) listener?.onErrorSearch(NetworkFailure(response.message(),
                        response.body().toString(),response.code()))}}, { t -> listener?.onErrorSearch(t)}))
    }

    override fun disposableInstance() {
        getDisposable().clear()
    }

}