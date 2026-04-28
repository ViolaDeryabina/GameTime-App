package com.example.gametime_app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gametime_app.R
import com.example.gametime_app.presentation.screens.CircleGame
import com.example.gametime_app.presentation.screens.CombatInformation
import com.example.gametime_app.presentation.screens.CreateAccount
import com.example.gametime_app.presentation.screens.DiscoverCombats
import com.example.gametime_app.presentation.screens.ImageGameScreen
import com.example.gametime_app.presentation.screens.LandingScreen
import com.example.gametime_app.presentation.screens.Login
import com.example.gametime_app.presentation.screens.MyProfile
import com.example.gametime_app.presentation.screens.Onboard
import com.example.gametime_app.presentation.screens.PlayerInformation
import com.example.gametime_app.presentation.screens.ProfileScreen
import com.example.gametime_app.presentation.screens.ScheduleGame
import com.example.gametime_app.presentation.screens.SpalshScreen
import com.example.gametime_app.presentation.screens.Statistics
import com.example.gametime_app.presentation.viewModel.AuthViewModel
import com.example.gametime_app.presentation.viewModel.CircleGameViewModel
import com.example.gametime_app.presentation.viewModel.CreateGameViewModel
import com.example.gametime_app.presentation.viewModel.LogoutViewModel
import com.example.gametime_app.presentation.viewModel.ProfileViewModel
import com.example.gametime_app.presentation.viewModel.RegistrationViewModel
import com.example.gametime_app.presentation.viewModel.StatViewModel
import com.example.gametime_app.presentation.viewModel.UpdateViewModel
import com.example.gametime_networklibrary.data.dataSources.Preferences
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_uikit.ui.theme.CustomTheme

@Composable
fun AppNavigation() {
    CustomTheme {
        val context = LocalContext.current
        val prefs = Preferences.get(context)
        val prefsRepository = AuthRepositoryImpl(prefs)
        val navController = rememberNavController()


        fun onClickDiscoverCombats() = navController.navigate(route = DiscoverCombats)
        fun onStatisticsClick() = navController.navigate(route = Statistics)
        fun onScheduleClick() = navController.navigate(route = ScheduleGame)

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = SplashScreen
            ) {
                composable<SplashScreen> {
                    SpalshScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClickOnboard = { navController.navigate(Onboard) },
                        onClickMain = { navController.navigate(LandingScreen) },
                        prefsRepository
                    )
                }
                composable<Onboard> {
                    Onboard(
                        modifier = Modifier.padding(innerPadding),
                        onClickRegister = { navController.navigate(CreateAccount) }
                    )
                }
                composable<CreateAccount> {
                    val viewModelRegister: RegistrationViewModel = hiltViewModel()
                    CreateAccount(
                        modifier = Modifier.padding(innerPadding),
                        viewModelRegister,
                        onClickMain = { navController.navigate(LandingScreen) },
                        onClickLogin = { navController.navigate(Login) },
                        prefs = prefsRepository
                    )
                }
                composable<Login> {
                    val viewModelAuth: AuthViewModel = hiltViewModel()
                    Login(
                        modifier = Modifier.padding(innerPadding),
                        viewModelAuth,
                        prefsRepository,
                        onClickMain = { navController.navigate(route = LandingScreen) },
                        onClickRegister = { navController.navigate(CreateAccount) }
                    )
                }
                composable<LandingScreen> {
                    val viewModel: LogoutViewModel = hiltViewModel()
                    val viewModelProfile: ProfileViewModel = hiltViewModel()
                    LandingScreen(
                        onClickScheduleGame = { navController.navigate(route = ScheduleGame) },
                        onClickDiscoverCombats = { navController.navigate(route = DiscoverCombats) },
                        onStatisticsClick = { onStatisticsClick() },
                        onScheduleClick = { onScheduleClick() },
                        onProfileScreen = { navController.navigate(route = MyProfile) },
                        onExit = { navController.navigate(route = Login) },
                        viewModel = viewModel,
                        prefs = prefsRepository,
                        viewModelProfile = viewModelProfile
                    )
                }
                composable<ScheduleGame> {
                    val createGameViewModel: CreateGameViewModel = hiltViewModel()
                    ScheduleGame(
                        modifier = Modifier.padding(innerPadding),
                        onClickMain = { navController.navigate(route = LandingScreen) },
                        viewModelGame = createGameViewModel,
                        prefs = prefsRepository
                    )
                }
                composable<DiscoverCombats> {
                    DiscoverCombats(
                        onClickCard = { navController.navigate(route = CombatInformation) },
                        onClickPopular = { navController.navigate(route = PlayerInformation) }
                    )

                }
                composable<CombatInformation> {
                    CombatInformation(
                        modifier = Modifier.padding(innerPadding),
                        onClickPlayerInfo = { navController.navigate(route = PlayerInformation) },
                        onClickBack = { navController.popBackStack() },
                        onCircleGame = { navController.navigate(route = CircleGame) },
                        onImageGame = { navController.navigate(route = ImageGameScreen) }
                    )
                }
                composable<PlayerInformation> {
                    val profileViewModel: ProfileViewModel = hiltViewModel()
                    PlayerInformation(
                        modifier = Modifier.padding(innerPadding),
                        onClickBack = { navController.popBackStack() },
                        profileViewModel,
                        prefsRepository

                    )
                }
                composable<Statistics> {
                    val statViewModel: StatViewModel = hiltViewModel()

                    Statistics(
                        onClickBack = { navController.popBackStack() },
                        prefs = prefsRepository,
                        viewModelStat = statViewModel
                    )
                }
                composable<CircleGame> {
                    val viewModel: CircleGameViewModel = hiltViewModel()
                    CircleGame(viewModel = viewModel)
                }
                composable<ImageGameScreen> {
                    ImageGameScreen(
                        bitmap = ImageBitmap.imageResource(R.drawable.dragon_image)
                            .asAndroidBitmap()
                    )
                }
                composable<ProfileScreen> {
                    ProfileScreen()
                }
                composable<MyProfile> {
                    val viewModel: ProfileViewModel = hiltViewModel()
                    val viewModelUpdate: UpdateViewModel = hiltViewModel()
                    MyProfile(
                        viewModel = viewModel,
                        viewModelUpdate = viewModelUpdate,
                        prefs = prefsRepository
                    )
                }
            }
        }
    }
}