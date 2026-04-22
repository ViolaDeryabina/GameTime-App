package com.example.gametime_app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.DomainError
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogoutViewModel: ViewModel() {

    private val _data = MutableStateFlow<Logout?>(null)
    val data: StateFlow<Logout?> = _data.asStateFlow()

    private val _error = MutableStateFlow<DomainError?>(null)
    val error: StateFlow<DomainError?> = _error.asStateFlow()

    fun logout(token: String) {
        _data.value = null
        viewModelScope.launch {
            when (val result = UseCases.logout(token)) {
                is NetworkResult.Success -> {
                    _data.value = result.data
                }

                is NetworkResult.Error -> {
                    _error.value = DomainError(result.status, result.message)
                }

                is NetworkResult.Exception -> {
                    _error.value = DomainError(null, result.e)
                }
            }
        }
    }
}