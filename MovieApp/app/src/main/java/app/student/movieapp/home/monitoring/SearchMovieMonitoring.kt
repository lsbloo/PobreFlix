package app.student.movieapp.home.monitoring

import android.util.Log
import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.model.NetworkSuccess
import app.student.movieapp.home.contract.SearchMovieContract

class SearchMovieMonitoring (): SearchMovieContract.Monitor {
    override fun trackNetworkFailure(network: NetworkFailure) {
        Log.d("Network-Failure", "\n Message: "+ network.message + "\n Body: " + network.body + " \n Status_Code: " + network.status_cod)
    }

    override fun trackNetworkError(networkError: NetworkError) {
        Log.d("Network-Error", " \n Message: " + networkError.message + " \n Trace: " +networkError.trace + " \n Throwlable: " + networkError.throwable)
    }

    override fun trackNetworkSuccess(network: NetworkSuccess) {
        Log.d("Network-Success", "\n Message: " + network.message + " \n Operation: " + network.operation)
    }

}