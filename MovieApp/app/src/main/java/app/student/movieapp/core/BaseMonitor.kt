package app.student.movieapp.core

import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.model.NetworkSuccess

interface BaseMonitor {
    fun trackNetworkFailure(network: NetworkFailure)
    fun trackNetworkError(networkError: NetworkError)
    fun trackNetworkSuccess(network: NetworkSuccess)
}