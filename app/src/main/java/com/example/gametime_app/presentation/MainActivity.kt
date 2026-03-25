package com.example.gametime_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gametime_app.presentation.navigation.CreateAccount
import com.example.gametime_app.presentation.navigation.Onboard
import com.example.gametime_app.presentation.navigation.Screens
import com.example.gametime_app.presentation.navigation.SplashScreen
import com.example.gametime_app.presentation.screens.CombatInformation
import com.example.gametime_app.presentation.screens.CreateAccount
import com.example.gametime_app.presentation.screens.DiscoverCombats
import com.example.gametime_app.presentation.screens.LandingScreen
import com.example.gametime_app.presentation.screens.Login
import com.example.gametime_app.presentation.screens.Onboard
import com.example.gametime_app.presentation.screens.PlayerInformation
import com.example.gametime_app.presentation.screens.ScheduleGame
import com.example.gametime_app.presentation.screens.SpalshScreen
import com.example.gametime_app.presentation.ui.theme.GameTimeAppTheme
import com.example.gametime_uikit.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CustomTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = SplashScreen
                    ) {
                        composable<SplashScreen> {
                            SpalshScreen(
                                modifier = Modifier.padding(innerPadding), navController
                            )
                        }
                        composable<Onboard> {
                            Onboard(
                                modifier = Modifier.padding(innerPadding), navController
                            )
                        }
                        composable<CreateAccount> {
                            CreateAccount(
                                modifier = Modifier.padding(innerPadding), navController
                            )
                        }
                        composable(Screens.Login.route) {
                            Login(
                                modifier = Modifier.padding(innerPadding), navController
                            )

                        }
                        composable(Screens.LandingScreen.route) {
                            LandingScreen(navController = navController)

                        }
                        composable(Screens.ScheduleGame.route) {
                            ScheduleGame(
                                modifier = Modifier.padding(innerPadding), navController
                            )
                        }//DiscoverCombats
                        composable(Screens.DiscoverCombats.route) {
                            DiscoverCombats()

                        }//CombatInformation
                        composable(Screens.CombatInformation.route) {
                            CombatInformation(
                                modifier = Modifier.padding(innerPadding),navController
                            )
                        }//PlayerInformation
                        composable(Screens.PlayerInformation.route) {
                            PlayerInformation(
                                modifier = Modifier.padding(innerPadding), navController
                            )
                        }
                    }
                }
            }
        }
    }
}