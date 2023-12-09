package com.example.arlibapps.data.repository

import com.example.arlibapps.data.database.LocalDataSource
import com.example.arlibapps.data.network.RemoteDataSource
import com.example.arlibapps.data.model.BaseApiResponse
import com.example.arlibapps.data.model.Medicines
import com.example.arlibapps.utilities.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BaseApiResponse() {

    suspend fun getMedicines(): Flow<NetworkResult<Medicines>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getMedicines() })
        }.flowOn(Dispatchers.IO)
    }
}

