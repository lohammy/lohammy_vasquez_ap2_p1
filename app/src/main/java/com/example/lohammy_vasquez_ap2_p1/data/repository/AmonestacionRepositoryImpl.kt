package com.example.lohammy_vasquez_ap2_p1.data.repository

import com.example.lohammy_vasquez_ap2_p1.data.local.AmonestacionDao
import com.example.lohammy_vasquez_ap2_p1.data.local.AmonestacionEntity
import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import com.example.lohammy_vasquez_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val amonestacionDao: AmonestacionDao
) : AmonestacionRepository {
    override suspend fun upsert(amonestacion: Amonestacion) {
        amonestacionDao.upsert(amonestacion.toEntity())
    }

    override suspend fun delete(amonestacion: Amonestacion) {
        amonestacionDao.delete(amonestacion.toEntity())
    }

    override suspend fun getAmonestacion(id: Int): Amonestacion? {
        return amonestacionDao.getAmonestacion(id)?.toAmonestacion()
    }

    override fun getAll(): Flow<List<Amonestacion>> {
        return amonestacionDao.getAll().map { entities ->
            entities.map { it.toAmonestacion() }
        }
    }
}

fun AmonestacionEntity.toAmonestacion() = Amonestacion(amonestacionId, nombres, razon, monto)
fun Amonestacion.toEntity() = AmonestacionEntity(amonestacionId, nombres, razon, monto)
