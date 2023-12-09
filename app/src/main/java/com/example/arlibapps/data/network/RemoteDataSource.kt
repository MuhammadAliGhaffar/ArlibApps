package com.example.arlibapps.data.network

import com.example.arlibapps.data.api.DialerApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiDialer: DialerApi) {

    suspend fun getMedicines() = apiDialer.getMedicines()

}