package com.example.lohammy_vasquez_ap2_p1.data.repository

import com.example.lohammy_vasquez_ap2_p1.data.local.RegistroDao
import com.example.lohammy_vasquez_ap2_p1.data.local.RegistroEntity
import com.example.lohammy_vasquez_ap2_p1.domain.model.Registro
import com.example.lohammy_vasquez_ap2_p1.domain.repository.RegistroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegistroRepositoryImpl @Inject constructor(
    private val registroDao: RegistroDao
) : RegistroRepository {
    override suspend fun upsertRegistro(registro: Registro) {
        registroDao.upsertRegistro(registro.toEntity())
    }

    override suspend fun deleteRegistro(registro: Registro) {
        registroDao.deleteRegistro(registro.toEntity())
    }

    override suspend fun getRegistro(id: Int): Registro? {
        return registroDao.getRegistro(id)?.toRegistro()
    }

    override fun getAllRegistros(): Flow<List<Registro>> {
        return registroDao.getAllRegistros().map { entities ->
            entities.map { it.toRegistro() }
        }
    }
}

fun RegistroEntity.toRegistro() = Registro(registroId, descripcion)
fun Registro.toEntity() = RegistroEntity(registroId, descripcion)
