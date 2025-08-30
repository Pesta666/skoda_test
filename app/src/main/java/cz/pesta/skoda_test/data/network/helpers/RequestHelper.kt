package cz.pesta.skoda_test.data.network.helpers

import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T : Any> callSafely(call: suspend () -> T): Result<T> {
    try {
        val data = call()
        return Result.Success(data)
    } catch (e: HttpException) {
        return when (e.code()) {
            400 -> Result.BadRequest(e.response()?.errorBody()?.string() ?: e.message(), e)
            401 -> Result.Unauthorized
            403 -> Result.Forbidden
            404 -> Result.NotFound
            422 -> Result.UnprocessableEntity
            423 -> Result.Locked
            500 -> Result.ServerError
            else -> Result.UnknownError(e)
        }
    } catch (e: UnknownHostException) {
        return Result.NetworkError
    } catch (e: Exception) {
        return Result.UnknownError(e)
    }
}