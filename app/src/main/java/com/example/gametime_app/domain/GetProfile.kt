package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.domain.entities.User
import com.example.gametime_networklibrary.domain.repository.ProfileRepository
import javax.inject.Inject

class GetProfile @Inject constructor(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(
        token: String
    ): NetworkResult<User> {
        return profileRepository.getProfile(token)
    }
}