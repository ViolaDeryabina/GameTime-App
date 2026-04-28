package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.gametime_app.domain.AuthUseCase

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _data = MutableStateFlow<NetworkResult<RegisterResponse>?>(null)
    val data: StateFlow<NetworkResult<RegisterResponse>?> = _data.asStateFlow()

    fun authorization(authRequest: AuthRequest) {
        _data.value = null
        viewModelScope.launch {
            when (val result = authUseCase.invoke(authRequest)) {
                is NetworkResult.Success -> {
                    _data.value = NetworkResult.Success(result.data)
                }

                is NetworkResult.Error -> {
                    _data.value = NetworkResult.Error(result.status, result.message)
                }

                is NetworkResult.Exception -> {
                    _data.value = NetworkResult.Exception(result.e)
                }
            }
        }
    }
}