package com.example.arlibapps.data.di

import android.content.Context
import androidx.room.Room
import com.example.arlibapps.data.database.LocalDataSource
import com.example.arlibapps.data.database.MedicinesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): LocalDataSource {
        return Room.databaseBuilder(
            context,
            LocalDataSource::class.java, "arlib-apps"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMedicinesDao(database: LocalDataSource): MedicinesDao {
        return database.MedicinesDao()
    }
}
