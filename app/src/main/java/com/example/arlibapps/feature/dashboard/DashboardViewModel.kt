package com.example.arlibapps.feature.dashboard

import androidx.lifecycle.ViewModel
import com.example.arlibapps.data.model.Medicine
import com.example.arlibapps.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        getMedicines()
    }

    private fun getMedicines() {
        _uiState.value.medicines = runBlocking {
            val deferred = async {
                repository.getMedicines()
            }
            deferred.await()
        }
    }
}