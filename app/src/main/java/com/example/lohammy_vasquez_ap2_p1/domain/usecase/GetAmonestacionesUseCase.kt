package com.example.lohammy_vasquez_ap2_p1.domain.usecase

import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import com.example.lohammy_vasquez_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAmonestacionesUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    operator fun invoke(): Flow<List<Amonestacion>> = repository.getAll()
    suspend operator fun invoke(id: Int): Amonestacion? = repository.getAmonestacion(id)
}
