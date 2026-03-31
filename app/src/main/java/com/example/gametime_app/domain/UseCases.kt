package com.example.gametime_app.domain

import com.example.gametime_networklibrary.data.dataSources.Apollo
import com.example.gametime_networklibrary.data.dataSources.NetworkService

object UseCases {
    val authorization = AuthUseCase(NetworkService.userRepository)
    val registration = RegisterUseCase(NetworkService.userRepository)
    val getCategory = GetCategoryUseCase(NetworkService.gameRepository)
    val createGameUseCase = CreateGameUseCase(NetworkService.gameRepository)

    val getStatisticUseCase = GetStatisticUseCase(apolloRepository = Apollo.apolloService)
}