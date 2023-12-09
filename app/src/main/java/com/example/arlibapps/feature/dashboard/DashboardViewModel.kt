package com.example.arlibapps.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arlibapps.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getMedicines()
    }

    private fun getMedicines() {
        viewModelScope.launch {
            repository.getMedicines()
        }
    }
}