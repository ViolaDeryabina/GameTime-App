package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.auth.Player
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest
import com.example.gametime_networklibrary.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(registrationRequest: RegistrationRequest): NetworkResult<Player>{
        return userRepository.registration(registrationRequest)
    }

}