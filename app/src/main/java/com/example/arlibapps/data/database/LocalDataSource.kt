package com.example.arlibapps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.arlibapps.data.model.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class LocalDataSource: RoomDatabase() {
    abstract fun MedicinesDao(): MedicinesDao
}