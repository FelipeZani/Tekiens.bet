package com.example.surveymobileapp.client

sealed class NetworkError{
    object ClientRequestException : Error()
    object ServerResponseException: Error()
    object IOException : Error()
    object SerializationException : Error()
}
