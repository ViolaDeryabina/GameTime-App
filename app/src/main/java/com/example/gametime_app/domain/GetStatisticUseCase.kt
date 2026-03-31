package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.domain.model.StatisticItem
import com.example.gametime_networklibrary.domain.repository.ApolloRepository

class GetStatisticUseCase(val apolloRepository: ApolloRepository) {
    suspend operator fun invoke(token: String): NetworkResult<List<StatisticItem>> {
        return apolloRepository.getStatistic(token)
    }
}