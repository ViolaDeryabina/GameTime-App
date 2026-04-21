package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import com.example.gametime_networklibrary.domain.repository.ProfileRepository

class GetProfile(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(
        token: String
    ): NetworkResult<User> {
        return profileRepository.getProfile(token)
    }
}