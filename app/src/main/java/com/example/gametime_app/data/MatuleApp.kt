package com.example.gametime_app.data

import com.example.gametime_networklibrary.data.dataSources.NetworkService
import com.example.gametime_networklibrary.data.dataSources.service.UserService

object MatuleApp {
    val authorization = AuthUseCase(NetworkService.userRepository)
    val registration = RegisterUseCase(NetworkService.userRepository)
    val getCategory = GetCategoryUseCase(NetworkService.gameRepository)
    val createGameUseCase = CreateGameUseCase(NetworkService.gameRepository)
}