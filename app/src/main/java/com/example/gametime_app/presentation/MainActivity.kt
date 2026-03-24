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
import com.example.gametime_app.data.Screens
import com.example.gametime_app.presentation.screens.Onboard
import com.example.gametime_app.presentation.screens.SpalshScreen
import com.example.gametime_app.presentation.ui.theme.GameTimeAppTheme
import com.example.gametime_uikit.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = Screens.SpalshScreen.route) {
                        composable(Screens.SpalshScreen.route) {
                            SpalshScreen(
                                modifier = Modifier.padding(innerPadding),navController
                            )
                        }
                        composable(Screens.Onboard.route) {
                            Onboard(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                    }

                }
            }
        }
    }
}