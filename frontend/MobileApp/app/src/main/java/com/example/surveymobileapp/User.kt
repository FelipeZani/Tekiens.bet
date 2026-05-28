package com.example.surveymobileapp

import kotlinx.serialization.Serializable

@Serializable
data class UserSignup (val name : String,val email : String, val password : String)
@Serializable
data class UserLogin(val email: String, val password: String )
@Serializable
data class UserInfoResponse(
    val userInfo: UserInfo,
    val message: String
)
 @Serializable
data class UserInfo(
    val id: String,
    val emailAddress: String
)
