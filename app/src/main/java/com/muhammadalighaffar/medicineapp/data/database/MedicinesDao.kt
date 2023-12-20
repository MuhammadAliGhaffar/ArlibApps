package com.muhammadalighaffar.medicineapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammadalighaffar.medicineapp.data.model.Medicine

@Dao
interface MedicinesDao {
    @Query("SELECT * FROM tbl_medicines")
    suspend fun getAll(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(medicine: List<Medicine>)
}