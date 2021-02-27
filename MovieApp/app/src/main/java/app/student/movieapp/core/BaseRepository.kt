package app.student.movieapp.core

import io.reactivex.disposables.CompositeDisposable

interface BaseRepository {

    fun <T> attachListener(t: T)
    fun getDisposable(): CompositeDisposable
}