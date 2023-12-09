package com.example.arlibapps.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.arlibapps.data.model.Medicine

@Dao
interface MedicinesDao {
    @Query("SELECT * FROM tbl_medicines")
    suspend fun getAll(): Medicine

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(medicine: Medicine)
}