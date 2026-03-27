package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gametime_app.R
import com.example.gametime_app.presentation.viewModel.RegistrationViewModel
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest
import com.example.gametime_uikit.ui.components.Input
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.theme.CustomTheme


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun CreateAccount(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    onClickMain: () -> Unit,
    onClickLogin: () -> Unit

) {

    val data by viewModel.data.collectAsStateWithLifecycle()
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        var fullName by rememberSaveable { mutableStateOf("") }
        var userName by remember { mutableStateOf("") }

        var phone by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        var visualTransformationPassword by remember { mutableStateOf(false) }
        var visualTransformationConfirmPassword by remember { mutableStateOf(false) }

        when (data) {
            is NetworkResult.Success -> {
                onClickMain()
            }
        }

        Column(
            modifier = modifier
                .verticalScroll(scroll)
                .fillMaxSize()
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerHeight(21)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 27.dp)
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.image_create_account),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp, end = 60.dp)
            ) {
                Text(
                    text = "Create Account",
                    modifier = Modifier.width(224.dp),
                    style = CustomTheme.typography.Title1ExtraBold24,
                    color = CustomTheme.colors.accent
                )
                SpacerHeight(12)
                Text(
                    text = "Hi, kindly fill in the form to proceed \n" +
                            "combat",
                    modifier = Modifier.width(224.dp),
                    style = CustomTheme.typography.Caption2Regular12,
                    color = CustomTheme.colors.black
                )
                SpacerHeight(30)
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


            SpacerHeight(44)
            PrimaryButton(onClick = {
                viewModel.registration(
                    RegistrationRequest(
                        email = email,
                        password = password,
                        passwordConfirm = confirmPassword,
                        fullName = fullName,
                        userName = userName,
                        phone = phone
                    )
                )
            }, text = "Create Account", modifier = Modifier
                .width(210.dp)
                .height(58.dp))

            SpacerHeight(23)
            Text(
                text = "Connect With:",
                style = CustomTheme.typography.Caption2Regular12,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.accent,
            )
            SpacerHeight(7)
            Row {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF34A38)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.google),
                        contentDescription = "",
                        tint = CustomTheme.colors.white,
                        modifier = Modifier.size(14.15.dp)

                    )
                }
                SpacerWidth(15)
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2672CB)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.facebook),
                        contentDescription = "",
                        tint = CustomTheme.colors.white,
                        modifier = Modifier.size(14.15.dp)

                    )
                }
            }

            SpacerHeight(24)
            TextButton(onClick = { onClickLogin() }) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colors.black
                            )
                        ) {
                            append("Already have an account?\n")
                        }
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colors.accent,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Login")
                        }
                    },
                    style = CustomTheme.typography.Caption2Regular12,
                    textAlign = TextAlign.Center
                )
            }
            SpacerHeight(39)
        }
    }
}

@Preview
@Composable
private fun CreateAccountPreview() {
    CustomTheme {
        //CreateAccount()
    }
}