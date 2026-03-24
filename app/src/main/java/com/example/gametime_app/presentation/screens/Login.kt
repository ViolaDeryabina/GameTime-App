package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.example.gametime_app.R
import com.example.gametime_app.data.Screens
import com.example.gametime_uikit.ui.components.Helpers
import com.example.gametime_uikit.ui.components.Input
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.theme.CustomTheme


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController) {
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var visualTransformationPassword by remember { mutableStateOf(false) }


        Column(
            modifier = modifier
                .verticalScroll(scroll)
                .fillMaxSize()
                .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Helpers.SpacerHeight(51)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 52.dp)
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.login),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Helpers.SpacerHeight(41)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 52.dp, end = 60.dp)
            ) {
                Text(
                    text = "Welcome Back!",
                    modifier = Modifier.width(224.dp),
                    style = CustomTheme.typography.Title1ExtraBold24,
                    color = CustomTheme.colors.accent
                )
                Helpers.SpacerHeight(12)
                Text(
                    text = "Hi, Kindly login to continue battle",
                    modifier = Modifier.width(224.dp),
                    style = CustomTheme.typography.Caption2Regular12,
                    color = CustomTheme.colors.black
                )


                Input(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.height(45.dp),
                    placeholder = "Email"
                )
                Helpers.SpacerHeight(9)
                Input(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.height(45.dp),
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = {}) {
                        Text(
                            text = "Forgot Password?",
                            style = CustomTheme.typography.Caption2Regular12,
                            textAlign = TextAlign.Center,
                            color = CustomTheme.colors.black
                        )
                    }
                }


            }


            Helpers.SpacerHeight(27)
            PrimaryButton(onClick = {}, text = "Let’s Combat!", modifier = Modifier.width(210.dp))
            Helpers.SpacerHeight(23)
            Text(
                text = "Connect With:",
                style = CustomTheme.typography.Caption2Regular12,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.accent,
            )
            Helpers.SpacerHeight(7)
            Row() {
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
                Helpers.SpacerWidth(15)
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

            Helpers.SpacerHeight(18)
            TextButton(onClick = {navController.navigate(Screens.CreateAccount.route)}) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colors.black
                            )
                        ) {
                            append("Don’t have an account? \n")
                        }
                        withStyle(
                            SpanStyle(
                                color = CustomTheme.colors.accent,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Create Account")
                        }
                    },
                    style = CustomTheme.typography.Caption2Regular12,
                    textAlign = TextAlign.Center
                )
            }
            Helpers.SpacerHeight(43)

        }
    }
}

@Preview
@Composable
private fun CreateAccountPreview() {
    CustomTheme {
        //Login()
    }
}