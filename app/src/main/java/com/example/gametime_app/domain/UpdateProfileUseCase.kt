package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.ProfileService
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfileRequest
import com.example.gametime_networklibrary.domain.repository.ProfileRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UpdateProfileUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(token: String, name: RequestBody, avatar: MultipartBody.Part): NetworkResult<UpdateProfile>  {
        return profileRepository.updateProfile(token, name,avatar)
    }
}