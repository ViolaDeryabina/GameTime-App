package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.auth.Player
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _data = MutableStateFlow<NetworkResult<Player>?>(null)
    val data: StateFlow<NetworkResult<Player>?> = _data.asStateFlow()

    fun registration(register: RegistrationRequest) {
        _data.value = null
        viewModelScope.launch {
            when (val result = UseCases.registration(register)) {
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