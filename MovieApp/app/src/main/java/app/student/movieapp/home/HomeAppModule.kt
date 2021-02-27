package app.student.movieapp

import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.MovieListResourceManager
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
            factory{ (view: MovieListContract.View) ->
                MovieListPresenter(view, get(),get())
            }
        }

      val searchMovieModule = module {


      }
    }
}