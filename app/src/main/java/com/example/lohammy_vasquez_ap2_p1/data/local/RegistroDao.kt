package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface RegistroDao {

    @Upsert
    suspend fun upsertRegistro(registro: BorrameEntity): Long
}