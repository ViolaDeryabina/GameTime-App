package com.example.gametime_app.presentation.navigation

import kotlinx.serialization.Serializable

open class Screens(val route: String) {
    data object Login: Screens("Login")
    data object LandingScreen: Screens("LandingScreen")
    data object ScheduleGame: Screens("ScheduleGame")
    data object DiscoverCombats: Screens("DiscoverCombats")
    data object CombatInformation: Screens("CombatInformation")
    data object PlayerInformation: Screens("PlayerInformation")
}

@Serializable object SplashScreen
@Serializable object Onboard
@Serializable object CreateAccount