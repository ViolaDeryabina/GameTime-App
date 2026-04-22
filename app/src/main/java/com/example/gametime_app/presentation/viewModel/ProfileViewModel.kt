package com.example.gametime_app.presentation.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.DomainError
import com.example.gametime_app.domain.UseCases
import com.example.gametime_app.domain.UseCases.updateProfile
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class ProfileViewModel : ViewModel() {
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
            when (val result = UseCases.getProfile(token)) {
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

    private val _dataUpdate = MutableStateFlow<UpdateProfile?>(null)
    val dataUpdate: StateFlow<UpdateProfile?> = _dataUpdate.asStateFlow()

    private val _errorUpdate = MutableStateFlow<DomainError?>(null)
    val errorUpdate: StateFlow<DomainError?> = _errorUpdate.asStateFlow()

//    fun updateProfiles(token: String, name: String,  avatar: MultipartBody.Part) {
//        _dataUpdate.value = null
//        viewModelScope.launch {
//            when (val result = UseCases.updateProfile(token, name,avatar)) {
//                is NetworkResult.Success -> {
//                    _dataUpdate.value = result.data
//                }
//
//                is NetworkResult.Error -> {
//                    _errorUpdate.value = DomainError(result.status, result.message)
//                }
//
//                is NetworkResult.Exception -> {
//                    _errorUpdate.value = DomainError(null, result.e)
//                }
//            }
//        }
//    }

    @SuppressLint("Recycle")
    fun updateProfile(token: String, name: String, uri: Uri?, context: Context) {
        viewModelScope.launch {
            // Читаем поток байтов из Uri
            if(uri!=null){
                val inputStream = context.contentResolver.openInputStream(uri)
                val requestFile = inputStream?.readAllBytes()?.let {
                    it.toRequestBody("image/*".toMediaTypeOrNull(), 0, it.size)
                } ?: return@launch

                // Создаем Part (имя "avatar" должно совпадать с тем, что ждет сервер)
                val body = MultipartBody.Part.createFormData("avatar", "filename.jpg", requestFile)
                val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())

                updateProfile(token, namePart, body)
            }

        }
    }
}