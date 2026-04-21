package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import com.example.gametime_networklibrary.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(registerRequest: RegisterRequest): NetworkResult<RegisterResponse>{
        return userRepository.registration(registerRequest)
    }

}