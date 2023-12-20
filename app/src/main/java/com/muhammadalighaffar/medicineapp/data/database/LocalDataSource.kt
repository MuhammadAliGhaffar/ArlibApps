package com.muhammadalighaffar.medicineapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammadalighaffar.medicineapp.data.model.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class LocalDataSource: RoomDatabase() {
    abstract fun MedicinesDao(): MedicinesDao
}