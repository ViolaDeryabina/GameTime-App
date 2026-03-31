package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _data = MutableStateFlow<NetworkResult<AuthResponse>?>(null)
    val data: StateFlow<NetworkResult<AuthResponse>?> = _data.asStateFlow()

    fun authorization(authRequest: AuthRequest) {
        _data.value = null
        viewModelScope.launch {
            when (val result = UseCases.authorization(authRequest)) {
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