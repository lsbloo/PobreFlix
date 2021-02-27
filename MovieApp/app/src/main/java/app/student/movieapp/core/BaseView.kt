package app.student.movieapp.core

import app.student.movieapp.core.model.NetworkFailure

interface BaseView {
    fun onMessageError(network: NetworkFailure)
    fun onMessageError(t: Throwable)
}