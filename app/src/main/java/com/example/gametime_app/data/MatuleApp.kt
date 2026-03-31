package com.example.gametime_app.data

import com.example.gametime_networklibrary.data.dataSources.Apollo
import com.example.gametime_networklibrary.data.dataSources.NetworkService
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.repositoryImpl.ApolloRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.ApolloRepository

object MatuleApp {
    val authorization = AuthUseCase(NetworkService.userRepository)
    val registration = RegisterUseCase(NetworkService.userRepository)
    val getCategory = GetCategoryUseCase(NetworkService.gameRepository)
    val createGameUseCase = CreateGameUseCase(NetworkService.gameRepository)

    val getStatisticUseCase = GetStatisticUseCase(apolloRepository = Apollo.apolloService)
}