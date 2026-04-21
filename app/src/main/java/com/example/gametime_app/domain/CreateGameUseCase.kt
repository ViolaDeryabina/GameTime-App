package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameResponse
import com.example.gametime_networklibrary.domain.repository.GameRepository

class CreateGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(
        token: String,
        createGameRequest: CreateGameRequest
    ): NetworkResult<CreateGameResponse> {
        return gameRepository.createGame(token, createGameRequest)
    }
}