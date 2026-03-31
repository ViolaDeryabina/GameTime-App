package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.game.GameRecord
import com.example.gametime_networklibrary.domain.repository.GameRepository

class CreateGameUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(
        token: String,
        createGameRequest: CreateGameRequest
    ): NetworkResult<GameRecord> {
        return gameRepository.createGame(token, createGameRequest)
    }
}