package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {

    @Upsert
    suspend fun upsert(amonestacion: AmonestacionEntity): Long

    @Delete
    suspend fun delete(amonestacion: AmonestacionEntity)

    @Query("SELECT * FROM Amonestaciones WHERE amonestacionId = :id")
    suspend fun getAmonestacion(id: Int): AmonestacionEntity?

    @Query("SELECT * FROM Amonestaciones")
    fun getAll(): Flow<List<AmonestacionEntity>>
    }


