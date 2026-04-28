package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import com.example.gametime_networklibrary.domain.repository.UserRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(authRequest: AuthRequest): NetworkResult<RegisterResponse>{
        return userRepository.authorization(authRequest)
    }
}