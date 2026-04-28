package com.example.gametime_app.presentation.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.gametime_app.R
import com.example.gametime_app.presentation.viewModel.ProfileViewModel
import com.example.gametime_app.presentation.viewModel.UpdateViewModel
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_uikit.domain.TabBarItem
import com.example.gametime_uikit.ui.components.Input
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.components.TapBar
import com.example.gametime_uikit.ui.theme.CustomTheme
import kotlin.let

@Composable
fun MyProfile(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel,
    viewModelUpdate: UpdateViewModel,
    prefs: AuthRepositoryImpl
) {
    val scroll = rememberScrollState()
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var visualTransformationPassword by remember { mutableStateOf(false) }
    var visualTransformationConfirmPassword by remember { mutableStateOf(false) }

    val data by viewModel.data.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    var token by remember { mutableStateOf("") }
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        token = prefs.getToken() ?: ""
        viewModel.getProfile(token)
    }

    Scaffold(bottomBar = {
        TapBar(
            leadingTabBarItems = listOf(
                TabBarItem(
                    index = 0,
                    title = "Statistics",
                    iconId = com.example.gametime_uikit.R.drawable.statistics_icon,
                    onClick = {}
                ),
                TabBarItem(
                    index = 1,
                    title = "Discover",
                    iconId = com.example.gametime_uikit.R.drawable.location_pin,
                    onClick = {}
                )
            ),
            trailingTabBarItems = listOf(
                TabBarItem(
                    index = 2,
                    title = "Chat",
                    iconId = com.example.gametime_uikit.R.drawable.chat,
                    onClick = {}
                ),
                TabBarItem(
                    index = 3,
                    title = "Profile",
                    iconId = com.example.gametime_uikit.R.drawable.profile,
                    onClick = {}
                )
            ),
            onScheduleClick = {},
            selectedIndex = 3
        )
    }) { inner ->
        loading?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CustomTheme.colors.white), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = CustomTheme.colors.accent,
                    trackColor = CustomTheme.colors.placeholder.copy(0.2f),
                )
            }
        }
        data?.let { d->
            val pickMedia =
                rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    if (uri != null) {
                        viewModel.imageURI = uri
                    } else {
                        Log.d("PhotoPicker", "No media selected")
                    }
                }
            val phoneText = prefs.getPhone() ?: ""
            val avatar = d.avatarUrl

            var fullName by rememberSaveable { mutableStateOf(d.name) }
            var phone by remember { mutableStateOf(phoneText) }
            var email by remember { mutableStateOf( d.email) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CustomTheme.colors.white)
                    .verticalScroll(scroll),
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 30.dp)
                        .padding(inner)
                        .background(CustomTheme.colors.white)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpacerHeight(67)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "My Profile",
                            style = CustomTheme.typography.Title1ExtraBold24,
                            color = CustomTheme.colors.accent,
                        )
                    }

                    SpacerHeight(41)
                    val uri = viewModel.imageURI
                    if (uri != null) {
                        AsyncImage(
                            model = viewModel.imageURI,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable(onClick = {
                                    pickMedia.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                })
                                .size(77.dp)
                                .clip(CircleShape)
                                .border(
                                    1.dp, CustomTheme.colors.accent,
                                    CircleShape
                                ),
                            contentScale = ContentScale.Crop,
                        )
                    } else if (avatar != null) {
                        AsyncImage(
                            model = avatar,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable(onClick = {
                                    pickMedia.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                })
                                .size(77.dp)
                                .clip(CircleShape)
                                .border(
                                    1.dp, CustomTheme.colors.accent,
                                    CircleShape
                                ),
                            contentScale = ContentScale.Crop,
                        )
                    } else {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.avatar_player_info),
                            contentDescription = "",
                            modifier = Modifier
                                .clickable(onClick = {
                                    pickMedia.launch(
                                        PickVisualMediaRequest(
                                            ActivityResultContracts.PickVisualMedia.ImageOnly
                                        )
                                    )
                                })
                                .size(77.dp)
                                .border(
                                    1.dp, CustomTheme.colors.accent,
                                    CircleShape
                                )
                        )
                    }

                    SpacerHeight(9)
                    Text(
                        d.name,
                        style = CustomTheme.typography.HeadlineMedium16,
                        color = CustomTheme.colors.black,
                    )
                    SpacerHeight(30)
                    Column(
                        modifier = Modifier.padding(horizontal = 25.dp)
                    ) {
                        Input(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp),
                            value = fullName,
                            onValueChange = { fullName = it },
                            placeholder = "Full Name"
                        )
                        SpacerHeight(9)
                        Input(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp),
                            value = userName,
                            onValueChange = { userName = it },
                            placeholder = "User Name"
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Input(
                                value = "+7",
                                onValueChange = { },
                                modifier = Modifier
                                    .height(45.dp)
                                    .weight(0.2f),
                                placeholder = "Email"
                            )
                            SpacerWidth(18)
                            Input(
                                value = phone,
                                onValueChange = { phone = it },
                                modifier = Modifier
                                    .height(45.dp)
                                    .weight(0.8f),
                                placeholder = "Your Phone"
                            )
                        }
                        Input(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp),
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Email"
                        )
                        SpacerHeight(9)
                        Input(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp),
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Password",
                            trailingIcon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.visible),
                                    contentDescription = "",
                                    modifier = Modifier.clickable(onClick = {
                                        visualTransformationPassword = !visualTransformationPassword
                                    })
                                )
                            },
                            visualTransformation = if (!visualTransformationPassword) PasswordVisualTransformation() else VisualTransformation.None
                        )
                        Input(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp),
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholder = "Confirm Password",
                            trailingIcon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.visible),
                                    contentDescription = "",
                                    modifier = Modifier.clickable(onClick = {
                                        visualTransformationConfirmPassword =
                                            !visualTransformationConfirmPassword
                                    })
                                )
                            },
                            visualTransformation = if (!visualTransformationConfirmPassword) PasswordVisualTransformation() else VisualTransformation.None
                        )
                    }

                    SpacerHeight(54)
                    PrimaryButton(
                        onClick = {
                            viewModelUpdate.updateProfile(
                                token,
                                fullName,
                                viewModel.imageURI,
                                context
                            )
                        },
                        text = "Update Account",
                        modifier = Modifier
                            .width(210.dp)
                            .height(58.dp)
                    )


                }
            }
        }

    }
}

@Preview
@Composable
private fun MyProfilePrev() {
    CustomTheme {
        //MyProfile()
    }
}