package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.RegisterUseCase
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val _data = MutableStateFlow<NetworkResult<RegisterResponse>?>(null)
    val data: StateFlow<NetworkResult<RegisterResponse>?> = _data.asStateFlow()

    fun registration(register: RegisterRequest) {
        _data.value = null
        viewModelScope.launch {
            when (val result = registerUseCase.invoke(register)) {
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