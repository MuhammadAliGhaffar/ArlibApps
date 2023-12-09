package com.example.arlibapps.feature.dashboard

import androidx.lifecycle.ViewModel
import com.example.arlibapps.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getMedicines()
    }

    private fun getMedicines() {
        runBlocking {
            this.launch {
                repository.getMedicines()
            }
        }
    }
}