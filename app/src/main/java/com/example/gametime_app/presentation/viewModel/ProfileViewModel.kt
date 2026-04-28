package com.example.gametime_app.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.GetProfile
import com.example.gametime_app.domain.dto.DomainError
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.domain.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (private val getProfileUseCase: GetProfile) : ViewModel() {
    var imageURI by mutableStateOf<Uri?>(null)

    private val _data = MutableStateFlow<User?>(null)
    val data: StateFlow<User?> = _data.asStateFlow()

    private val _error = MutableStateFlow<DomainError?>(null)
    val error: StateFlow<DomainError?> = _error.asStateFlow()

    private val _loading = MutableStateFlow<Boolean?>(null)
    val loading: StateFlow<Boolean?> = _loading.asStateFlow()

    fun getProfile(token: String) {
        _data.value = null
        _loading.value = true
        viewModelScope.launch {
            when (val result = getProfileUseCase.invoke(token)) {
                is NetworkResult.Success -> {
                    _data.value = result.data
                    _loading.value = false
                }

                is NetworkResult.Error -> {
                    _error.value = DomainError(result.status, result.message)
                    _loading.value = false
                }

                is NetworkResult.Exception -> {
                    _error.value = DomainError(null, result.e)
                    _loading.value = false
                }
            }
        }
    }
}