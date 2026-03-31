package com.example.gametime_app.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime_app.domain.UseCases
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.domain.model.StatisticItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GetStatisticViewModel : ViewModel() {
    private val _data = MutableStateFlow<NetworkResult<List<StatisticItem>>?>(null)
    val data: StateFlow<NetworkResult<List<StatisticItem>>?> = _data.asStateFlow()

    fun getStatistic(token: String) {
        _data.value = null
        viewModelScope.launch {
            when (val result = UseCases.getStatisticUseCase(token)) {
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