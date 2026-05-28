package com.example.surveymobileapp.client

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking


sealed class HttpRequestsErrors {
    data class ClientRequestError(val statusCode: Int): Error()
    data class SerializationError(val passedMessage: String): Error(){
        override val message: String = "Serialization Error detected : \"$passedMessage\""
    }
    data class IOError(val passedMessage: String): Error(){
        override val message: String = "IOError Error detected : \"$passedMessage\""
    }
    data class ServerResponseError(val passedMessage: String): Error(){
        override val message: String = "ServerResponseError Error detected : \"$passedMessage\""
    }

}//^\{ \"[a-z]+\":\"[a-z]\",\)*(Chf| \"\0\")? \}$


suspend fun getResponseContent(response : HttpResponse?) = checkHttpRequestStatus(response).fold(
    ifLeft = { error-> error.message },
    ifRight = {response?.bodyAsText()}
)
suspend fun checkHttpRequestStatus(response : HttpResponse?): Either<Error, String> {
    if(response == null){
        println("Response error")
        return Either.Left(Error("Error 500, something went wrong with the server, message from EitherException.kt"));
    }

    val clientRequestErrorArr = arrayOf(401, 404, 409, 422,405, 412, 415)

    val responseCode = response.status.value

    if (responseCode in clientRequestErrorArr){
        return Either.Left(HttpRequestsErrors.ClientRequestError(responseCode))
    }
    if (responseCode == 400){
        return Either.Left(HttpRequestsErrors.SerializationError("400"))
    }
    if (responseCode == 403){
        return Either.Left(HttpRequestsErrors.IOError("403"))
    }
    if(responseCode == 500){
        return Either.Left(HttpRequestsErrors.IOError("500"))

    }

    return Either.Right(response.body())
}
