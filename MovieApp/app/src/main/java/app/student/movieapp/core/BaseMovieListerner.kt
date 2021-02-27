package app.student.movieapp.core

import app.student.movieapp.core.model.NetworkFailure

interface BaseMovieListerner {

    fun onErrorSearch(t: Throwable)
    fun onErrorSearch(f: NetworkFailure)
}