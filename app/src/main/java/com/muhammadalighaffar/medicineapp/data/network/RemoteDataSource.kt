package com.muhammadalighaffar.medicineapp.data.network

import com.muhammadalighaffar.medicineapp.data.api.DialerApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiDialer: DialerApi) {

    suspend fun getMedicines() = apiDialer.getMedicines()

}