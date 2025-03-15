package com.example.smarthome.network

import android.content.Context
import com.example.smarthome.model.ApiResponse
import com.example.smarthome.model.LoginRequest
import com.example.smarthome.model.RegisterRequest
import com.example.smarthome.model.ForgotPasswordRequest
import com.example.smarthome.model.VerifyCodeRequest
import com.example.smarthome.model.ChangePasswordRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.gson.*

object KtorClient {
    // Replace with your server's IP if not using emulator localhost
    const val BASE_URL = "http://10.0.2.2/api/" // For emulator; use real IP for device
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson {
                setLenient()
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
    }

    val authApiService = AuthApiService(client)
}

class AuthApiService(private val client: HttpClient = KtorClient.client) {

    suspend fun requestPasswordReset(request: ForgotPasswordRequest): Pair<Int, ApiResponse> {
        try {
            println("Sending password reset request to ${KtorClient.BASE_URL}forgot_password.php with $request")
            val response = client.post("${KtorClient.BASE_URL}forgot_password.php") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val rawResponse = response.bodyAsText()
            println("Raw response: $rawResponse")
            val apiResponse: ApiResponse = response.body()
            println("Parsed response: $apiResponse")
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Request password reset error: ${e.message}")
            throw e
        }
    }

    // Other methods remain unchanged unless needed
    suspend fun login(request: LoginRequest, context: Context): Pair<Int, ApiResponse> {
        println("Sending login request to ${KtorClient.BASE_URL}login.php with $request")
        try {
            val response = client.post("${KtorClient.BASE_URL}login.php") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val apiResponse: ApiResponse = response.body()
            if (apiResponse.success && apiResponse.token != null) {
                val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("user_email", request.Email)
                    putString("jwt_token", apiResponse.token)
                    apply()
                }
                val userName = getUserName(request.Email)
                if (userName != null) {
                    with(sharedPref.edit()) {
                        putString("user_name", userName)
                        apply()
                    }
                }
            }
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Login error: ${e.message}")
            throw e
        }
    }

    suspend fun verifyToken(token: String): Pair<Int, ApiResponse> {
        try {
            val response = client.post("${KtorClient.BASE_URL}verify.php") {
                contentType(ContentType.Application.Json)
                header("Authorization", "Bearer $token")
            }
            val apiResponse: ApiResponse = response.body()
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Verify token error: ${e.message}")
            throw e
        }
    }

    suspend fun register(request: RegisterRequest): Pair<Int, ApiResponse> {
        println("Sending register request to ${KtorClient.BASE_URL}register.php with $request")
        try {
            val response = client.post("${KtorClient.BASE_URL}register.php") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val apiResponse: ApiResponse = response.body()
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Register error: ${e.message}")
            throw e
        }
    }

    suspend fun getUserName(email: String): String? {
        println("Fetching user name for email: $email")
        try {
            val response = client.post("${KtorClient.BASE_URL}getUserName.php") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("email" to email))
            }
            val apiResponse: ApiResponse = response.body()
            return if (apiResponse.success) apiResponse.name else null
        } catch (e: Exception) {
            println("Error fetching user name: ${e.message}")
            return null
        }
    }

    suspend fun verifyResetCode(request: VerifyCodeRequest): Pair<Int, ApiResponse> {
        try {
            val response = client.post("${KtorClient.BASE_URL}verify_code.php") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val rawResponse = response.bodyAsText()
            println("Raw response from verify_code: $rawResponse")
            val apiResponse: ApiResponse = response.body()
            println("Parsed response from verify_code: $apiResponse")
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Verify reset code error: ${e.message}")
            throw e
        }
    }

    suspend fun changePassword(request: ChangePasswordRequest): Pair<Int, ApiResponse> {
        try {
            val response = client.post("${KtorClient.BASE_URL}change_password.php") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val rawResponse = response.bodyAsText()
            println("Raw response from change_password: $rawResponse")
            val apiResponse: ApiResponse = response.body()
            println("Parsed response from change_password: $apiResponse")
            return Pair(response.status.value, apiResponse)
        } catch (e: Exception) {
            println("Change password error: ${e.message}")
            throw e
        }
    }
}