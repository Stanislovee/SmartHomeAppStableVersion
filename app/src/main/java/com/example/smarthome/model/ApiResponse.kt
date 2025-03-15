package com.example.smarthome.model

data class LoginRequest(
    val Email: String,
    val Password: String
)

data class RegisterRequest(
    val Name: String,
    val Email: String,
    val Password: String,
    val token: String? = null
)

data class ForgotPasswordRequest(
    val email: String
)

data class VerifyCodeRequest(
    val email: String,
    val code: String
)

data class ChangePasswordRequest(
    val email: String,
    val code: String,
    val newPassword: String
)

data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val name: String? = null,
    val token: String? = null
)