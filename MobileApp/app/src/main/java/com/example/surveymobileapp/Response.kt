package com.example.surveymobileapp

import kotlinx.serialization.Serializable

@Serializable
data class Response(val token : String)

data class ErrorResponse(
    val code: Int,
    val message: String
)
