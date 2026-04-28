package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.LogoutUseCase
import com.example.gametime_app.domain.dto.DomainError
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase): ViewModel() {

    private val _data = MutableStateFlow<Logout?>(null)
    val data: StateFlow<Logout?> = _data.asStateFlow()

    private val _error = MutableStateFlow<DomainError?>(null)
    val error: StateFlow<DomainError?> = _error.asStateFlow()

    fun logout(token: String) {
        _data.value = null
        viewModelScope.launch {
            when (val result = logoutUseCase.invoke(token)) {
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