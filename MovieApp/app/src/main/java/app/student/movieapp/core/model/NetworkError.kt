package app.student.movieapp.core.model

data class NetworkError(val throwable: Throwable){

    var message: String? = throwable.message.toString()
    var trace: String?= throwable.cause!!.message


    companion object {
        const val MESSAGE_ERROR_RESPONSE=""
    }
}
data class NetworkFailure(val message: String, val body: String, val status_cod: Int)
