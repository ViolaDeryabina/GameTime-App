package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameData
import com.example.gametime_networklibrary.data.repositoryImpl.StatisticsRepositoryImpl
import javax.inject.Inject

class GetStatisticsGameUseCase @Inject constructor(private val statisticsRepository: StatisticsRepositoryImpl) {
    suspend operator fun invoke(token: String): NetworkResult<GameData>{
        return statisticsRepository.getStatisticsGame(token)

    }
}