package com.muhammadalighaffar.medicineapp.data.repository

import android.util.Log
import com.muhammadalighaffar.medicineapp.data.database.LocalDataSource
import com.muhammadalighaffar.medicineapp.data.model.Medicine
import com.muhammadalighaffar.medicineapp.data.network.RemoteDataSource
import com.muhammadalighaffar.medicineapp.utilities.BaseApiResponse
import com.muhammadalighaffar.medicineapp.utilities.NetworkResult
import com.muhammadalighaffar.medicineapp.utilities.internetIsConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BaseApiResponse() {

    suspend fun getMedicines(): List<Medicine> {
        return when {
            fetchMedicinesFromLocal().isNotEmpty() -> fetchMedicinesFromLocal()
            internetIsConnected() -> fetchMedicinesFromNetwork()
            else -> fetchMedicinesFromLocal()
        }
    }

    private suspend fun fetchMedicinesFromNetwork(): List<Medicine> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMedicines() })
        }.flowOn(Dispatchers.IO).transform { value ->
            when (value) {
                is NetworkResult.Success -> {
                    value.data?.let {
                        Log.d("_debug", "Data fetched from network: ${it.medicines}")
                        localDataSource.MedicinesDao().insert(it.medicines)
                        emitAll(localDataSource.MedicinesDao().getAll().asFlow())
                    }
                }

                is NetworkResult.Loading -> {
                    Log.d("_debug", "Loading")
                }

                is NetworkResult.Error -> {
                    Log.d("_debug", "Error ${value.message}")
                }
            }
        }.toList()
    }

    private suspend fun fetchMedicinesFromLocal(): List<Medicine> {
        val localMedicines = localDataSource.MedicinesDao().getAll()
        Log.d("_debug", "Data fetched from local: $localMedicines")
        return localMedicines
    }

}

