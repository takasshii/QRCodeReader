package com.example.qrcodereader.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.qrcodereader.QRCodeStringResultPreference
import com.example.qrcodereader.data.dataStore.ArgsRepository
import com.example.qrcodereader.data.dataStore.ArgsRepositoryImpl
import com.example.qrcodereader.data.dataStore.ArgsSerializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArgsModule {
    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        argsSerializer: ArgsSerializer
    ): DataStore<QRCodeStringResultPreference> =
        DataStoreFactory.create(
            serializer = argsSerializer,
        ) {
            context.dataStoreFile("args.pb")
        }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ArgsRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindArgsRepository(
        impl: ArgsRepositoryImpl
    ): ArgsRepository
}
