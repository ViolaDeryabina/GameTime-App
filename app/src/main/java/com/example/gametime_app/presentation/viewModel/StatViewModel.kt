package com.example.gametime_app.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.DomainError
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatViewModel: ViewModel() {
    private val _data = MutableStateFlow<GameData?>(null)
    val data: StateFlow<GameData?> = _data.asStateFlow()

    private val _error = MutableStateFlow<DomainError?>(null)
    val error: StateFlow<DomainError?> = _error.asStateFlow()

    fun getStat(token: String){
        viewModelScope.launch {
            when(val result = UseCases.getStatistic(token)){
                is NetworkResult.Success->{
                    Log.i("StatViewModel","${result.data.data.total}")
                    Log.i("StatViewModel","${result.data.data.image_puzzle}")
                    Log.i("StatViewModel","${result.data.data.ring_puzzle}")
                    _data.value = result.data
                }
                is NetworkResult.Error->{
                    Log.e("StatViewModel","${result.status}, ${result.message}")
                    _error.value = DomainError(result.status, result.message)
                }
                is NetworkResult.Exception->{
                    Log.e("StatViewModel","${result.e}")
                    _error.value = DomainError(null, result.e)
                }
            }
        }
    }
}