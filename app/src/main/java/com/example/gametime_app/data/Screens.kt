package com.example.gametime_app.data

open class Screens(val route: String) {
    data object SplashScreen: Screens("Screens")
    data object Onboard: Screens("Onboard")
    data object CreateAccount: Screens("CreateAccount")
    data object Login: Screens("Login")


}