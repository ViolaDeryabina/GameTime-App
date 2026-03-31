package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.game.CategoryList
import com.example.gametime_networklibrary.domain.repository.GameRepository

class GetCategoryUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(token: String): NetworkResult<CategoryList>{
        return gameRepository.getListCategory(token)
    }
}