package com.example.lohammy_vasquez_ap2_p1.domain.repository

import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    suspend fun upsert(amonestacion: Amonestacion)
    suspend fun delete(amonestacion: Amonestacion)
    suspend fun getAmonestacion(id: Int): Amonestacion?
    fun getAll(): Flow<List<Amonestacion>>
}
