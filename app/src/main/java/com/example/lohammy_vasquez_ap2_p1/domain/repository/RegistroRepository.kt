package com.example.lohammy_vasquez_ap2_p1.domain.repository

import com.example.lohammy_vasquez_ap2_p1.domain.model.Registro
import kotlinx.coroutines.flow.Flow

interface RegistroRepository {
    suspend fun upsertRegistro(registro: Registro)
    suspend fun deleteRegistro(registro: Registro)
    suspend fun getRegistro(id: Int): Registro?
    fun getAllRegistros(): Flow<List<Registro>>
}
