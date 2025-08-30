package cz.pesta.skoda_test.data.network.helpers

import retrofit2.HttpException

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class BadRequest(val message: String? = null, val e: HttpException) : Result<Nothing>()
    object Unauthorized : Result<Nothing>()
    object Forbidden : Result<Nothing>()
    object NotFound : Result<Nothing>()
    object UnprocessableEntity : Result<Nothing>()
    object Locked : Result<Nothing>()
    object ServerError : Result<Nothing>()
    object NetworkError : Result<Nothing>()
    data class UnknownError(val exception: Exception? = null) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is BadRequest -> "BadRequest[message=$message]"
            is Unauthorized -> "Unauthorized"
            is Forbidden -> "Forbidden"
            is NotFound -> "NotFound"
            is UnprocessableEntity -> "UnprocessableEntity"
            is Locked -> "Locked"
            is ServerError -> "ServerError"
            is NetworkError -> "NetworkError"
            is UnknownError -> "UnknownError[exception=$exception]"
        }
    }
}
