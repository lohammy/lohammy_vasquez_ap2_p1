package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroDao {

    @Upsert
    suspend fun upsertRegistro(registro: RegistroEntity): Long

    @Delete
    suspend fun deleteRegistro(registro: RegistroEntity)

    @Query("SELECT * FROM Registros WHERE registroId = :id")
    suspend fun getRegistro(id: Int): RegistroEntity?

    @Query("SELECT * FROM Registros")
    fun getAllRegistros(): Flow<List<RegistroEntity>>
}
