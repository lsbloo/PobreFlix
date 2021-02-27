package app.student.movieapp

import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.MovieListResourceManager
import app.student.movieapp.home.contract.SearchMovieContract
import app.student.movieapp.home.monitoring.SearchMovieMonitoring
import app.student.movieapp.home.presenter.SearchMoviePresenter
import app.student.movieapp.home.repository.SearchMovieRepository
import app.student.movieapp.home.repository.SearchMovieRepositoryImpl
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
          single<SearchMovieContract.Monitor> { SearchMovieMonitoring() }
          single<SearchMovieRepository> {SearchMovieRepositoryImpl()}
          factory {
              ( view: SearchMovieContract.View) ->
              SearchMoviePresenter(get(),get(),get())
          }

      }
    }
}