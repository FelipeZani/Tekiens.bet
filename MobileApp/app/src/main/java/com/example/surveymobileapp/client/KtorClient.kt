package com.example.surveymobileapp.client

import com.example.surveymobileapp.UserLogin
import com.example.surveymobileapp.UserSignup
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.appendPathSegments
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.util.logging.Logger
import kotlin.math.log

class KtorClient(val httpClient: HttpClient){


    suspend fun login(path : String, user : UserLogin ) : HttpResponse?{
        val loginResponse = httpClient.get(path){
            headers{
                append("Content-Type", "application/json; utf-8")
            }
            url{
                parameters.append("user",Json.encodeToString(user))
            }

        }
        checkHttpRequestStatus(loginResponse).fold(
            ifLeft = { error ->
                println("Error $error")

            },
            ifRight = {
                

            }
        )


        return try{
            println("creating post request for ${user} in order to login")
            httpClient.get(path){
                headers{
                    append("Content-Type", "application/json; utf-8")
                }
                url{
                    parameters.append("user",Json.encodeToString(user))
                }

            }
        }catch (e: ClientRequestException){
            println(e.message + " due to : "+e.cause)
            null
        }
    }
    suspend fun signup(path : String, user: UserSignup): HttpResponse? {


        return try {
            println("creating post request for ${user} in order to sign up")
            httpClient.post(path){

                headers{
                    append("Content-Type", "application/json; utf-8")
                }
                setBody(user)
            }
        }catch (e: ClientRequestException) {
            println(e.message + " due to : "+e.cause)
            null
//            ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
        } catch (e: ServerResponseException) {
            println(e.message + " due to : "+e.cause)
            null
//            ApiResponse.Error.HttpError(e.response.status.value, e.errorBody())
        } catch (e: IOException) {
            println(e.message + " due to : "+e.cause)
            null
//            ApiResponse.Error.NetworkError
        } catch (e: SerializationException) {
            println(e.message + " due to : "+e.cause)
            null
//            ApiResponse.Error.SerializationError
        }




    }


}