package com.example.smarthome

import android.content.Context
import com.example.smarthome.model.ApiResponse
import com.example.smarthome.model.LoginRequest
import com.example.smarthome.model.RegisterRequest
import com.example.smarthome.model.ForgotPasswordRequest
import com.example.smarthome.model.VerifyCodeRequest
import com.example.smarthome.model.ChangePasswordRequest
import com.example.smarthome.network.AuthApiService
import com.example.smarthome.network.KtorClient

interface AuthRepository {
    suspend fun login(email: String, password: String, context: Context): Pair<Int, ApiResponse>
    suspend fun register(name: String, email: String, password: String): Pair<Int, ApiResponse>
    suspend fun verifyToken(token: String): Pair<Int, ApiResponse>
    suspend fun requestPasswordReset(email: String): Pair<Int, ApiResponse>
    suspend fun verifyResetCode(email: String, code: String): Pair<Int, ApiResponse>
    suspend fun changePassword(email: String, code: String, newPassword: String): Pair<Int, ApiResponse>
    suspend fun getUserName(email: String): String?
}

class AuthRepositoryImpl(private val apiService: AuthApiService = KtorClient.authApiService) : AuthRepository {

    override suspend fun login(email: String, password: String, context: Context): Pair<Int, ApiResponse> {
        val request = LoginRequest(Email = email, Password = password)
        return apiService.login(request, context)
    }

    override suspend fun register(name: String, email: String, password: String): Pair<Int, ApiResponse> {
        val request = RegisterRequest(Name = name, Email = email, Password = password)
        return apiService.register(request)
    }

    override suspend fun verifyToken(token: String): Pair<Int, ApiResponse> {
        return apiService.verifyToken(token)
    }

    override suspend fun requestPasswordReset(email: String): Pair<Int, ApiResponse> {
        val request = ForgotPasswordRequest(email = email)
        return apiService.requestPasswordReset(request)
    }

    override suspend fun verifyResetCode(email: String, code: String): Pair<Int, ApiResponse> {
        val request = VerifyCodeRequest(email = email, code = code)
        return apiService.verifyResetCode(request)
    }

    override suspend fun changePassword(email: String, code: String, newPassword: String): Pair<Int, ApiResponse> {
        val request = ChangePasswordRequest(email = email, code = code, newPassword = newPassword)
        return apiService.changePassword(request)
    }

    override suspend fun getUserName(email: String): String? {
        return apiService.getUserName(email)
    }
}