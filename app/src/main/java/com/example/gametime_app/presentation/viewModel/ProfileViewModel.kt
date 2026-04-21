package com.example.gametime_app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.DomainError
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import com.example.gametime_networklibrary.domain.model.StatisticItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var imageURI by mutableStateOf<Uri?>(null)

    private val _data = MutableStateFlow<User?>(null)
    val data: StateFlow<User?> = _data.asStateFlow()

    private val _error = MutableStateFlow<DomainError?>(null)
    val error: StateFlow<DomainError?> = _error.asStateFlow()

    fun getProfile(token: String) {
        _data.value = null
        viewModelScope.launch {
            when (val result = UseCases.getProfile(token)) {
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