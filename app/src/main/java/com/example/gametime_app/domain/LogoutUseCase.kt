package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import com.example.gametime_networklibrary.domain.repository.UserRepository

class LogoutUseCase (private val userRepository: UserRepository) {
    suspend operator fun invoke(token: String): NetworkResult<Logout>{
        return userRepository.logout(token)
    }

}