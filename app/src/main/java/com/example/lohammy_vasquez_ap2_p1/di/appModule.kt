package com.example.lohammy_vasquez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.lohammy_vasquez_ap2_p1.data.local.AmonestacionDao
import com.example.lohammy_vasquez_ap2_p1.data.local.AmonestacionDb
import com.example.lohammy_vasquez_ap2_p1.data.repository.AmonestacionRepositoryImpl
import com.example.lohammy_vasquez_ap2_p1.domain.repository.AmonestacionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AmonestacionDb {
        return Room.databaseBuilder(
            context,
            AmonestacionDb::class.java,
            "Amonestacion.db"
        ).build()
    }

    @Provides
    fun provideAmonestacionDao(db: AmonestacionDb): AmonestacionDao {
        return db.amonestacionDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        amonestacionRepositoryImpl: AmonestacionRepositoryImpl
    ): AmonestacionRepository
}


