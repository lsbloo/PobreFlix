package app.student.movieapp.home.repository

import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.network.ApiService
import app.student.movieapp.core.network.RetrofitInitializer
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.storage.dao.SearchedMoviesDAO
import app.student.movieapp.home.storage.entity.SearchedMovies
import app.student.movieapp.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchMovieRepositoryImpl(private val searchedMoviesDao: SearchedMoviesDAO): SearchMovieRepository {
    private val retrofit = RetrofitInitializer()
    private val disposable = CompositeDisposable()
    private var listener: SearchMovieContract.MovieSearchListener? =  null


    override fun addRecentSearchMoviesStorage(searchedMovies: SearchedMovies): Boolean {
        searchedMoviesDao.add(searchedMovies)
        return true
    }

    override fun getAllRecentSearchMoviesStorage(): List<SearchedMovies> {
        return searchedMoviesDao.getAll()
    }

    override fun deleteRecentSearchMoviesStorage(searchedMovies: SearchedMovies): Boolean {
        searchedMoviesDao.remove(searchedMovies)
        return true
    }


    override fun getDetailsMovie(movie: Movie) {
        retrofit.apiServiceMovie().getDetaisMovie(ApiService.TOKEN_API,movie_id = movie.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({response -> response.body()?.let { listener?.detailMovieSearch(it)
                        if(!response.isSuccessful){listener?.onErrorSearch(NetworkFailure(response.message(),response.body().toString(),response.code()))
                        }
            }},{t ->  listener?.onErrorSearch(t)})
    }


    override fun <T> attachListener(t: T) {
        listener = t as SearchMovieContract.MovieSearchListener
    }

    override fun getDisposable(): CompositeDisposable {
        return disposable
    }

    override fun searchMovieByName(name: String, page: Int) {
        retrofit.apiServiceMovie().getMoviesBySearchName(name,ApiService.TOKEN_API,page)
            .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({ response -> response.body()?.listMovies?.let { listener?.listMoviesSearch(it)
                        if(!response.isSuccessful){listener?.onErrorSearch(NetworkFailure(response.message(),response.body().toString(), response.code())) }
            }},{t -> listener?.onErrorSearch(t)})}


}

