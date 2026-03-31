package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthResponse
import com.example.gametime_networklibrary.domain.repository.UserRepository

class AuthUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(authRequest: AuthRequest): NetworkResult<AuthResponse>{
        return userRepository.authorization(authRequest)
    }
}