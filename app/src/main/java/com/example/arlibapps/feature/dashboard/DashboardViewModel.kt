package com.example.arlibapps.feature.dashboard

import androidx.lifecycle.ViewModel
import com.example.arlibapps.data.model.Medicine
import com.example.arlibapps.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getMedicines():List<Medicine> {
        return runBlocking {
            val deferred = async {
                repository.getMedicines()
            }
            deferred.await()
        }
    }
}