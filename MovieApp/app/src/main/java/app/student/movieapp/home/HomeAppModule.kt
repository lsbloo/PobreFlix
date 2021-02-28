package app.student.movieapp

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.MovieListResourceManager
import app.student.movieapp.core.database.AppDatabase
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.monitoring.SearchMovieMonitoring
import app.student.movieapp.home.presenter.SearchMoviePresenter
import app.student.movieapp.home.repository.SearchMovieRepository
import app.student.movieapp.home.repository.SearchMovieRepositoryImpl
import app.student.movieapp.home.storage.dao.SearchedMoviesDAO
import app.student.movieapp.monitoring.MovieListMonitoring
import app.student.movieapp.presenter.MovieListPresenter
import app.student.movieapp.repository.MovieListRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class HomeAppModule {
    companion object {
        val movieListModule = module {

            single { MovieListRepositoryImpl() }
            single { MovieListMonitoring() }
            factory { (view: MovieListContract.View) ->
                MovieListPresenter(view, get(), get())
            }
        }

        val searchMovieModule = module {
            single { SearchMovieRepositoryImpl(newInstanceAppDatabase(get())) }
            single { SearchMovieMonitoring() }
            factory { (view: SearchMovieContract.View) ->
                SearchMoviePresenter(view, get(), get())
            }

        }

        private fun newInstanceAppDatabase(context: Context): SearchedMoviesDAO{
            val database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "pobreflixapp").allowMainThreadQueries().build()

            return database.searchedMoviesDAO()
        }
    }
}