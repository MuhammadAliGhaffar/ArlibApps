package com.muhammadalighaffar.medicineapp.feature.dashboard

import androidx.lifecycle.ViewModel
import com.muhammadalighaffar.medicineapp.data.model.Medicine
import com.muhammadalighaffar.medicineapp.data.repository.Repository
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