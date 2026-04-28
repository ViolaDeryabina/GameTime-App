package com.example.gametime_app.presentation.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.UpdateProfileUseCase
import com.example.gametime_app.domain.dto.DomainError
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(private val updateProfileUseCase: UpdateProfileUseCase): ViewModel() {
    private val _dataUpdate = MutableStateFlow<UpdateProfile?>(null)
    val dataUpdate: StateFlow<UpdateProfile?> = _dataUpdate.asStateFlow()

    private val _errorUpdate = MutableStateFlow<DomainError?>(null)
    val errorUpdate: StateFlow<DomainError?> = _errorUpdate.asStateFlow()

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

                updateProfileUseCase.invoke(token, namePart, body)
            }

        }
    }
}