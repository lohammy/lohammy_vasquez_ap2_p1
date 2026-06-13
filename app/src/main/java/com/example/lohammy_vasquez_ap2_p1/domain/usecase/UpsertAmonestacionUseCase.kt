package com.example.lohammy_vasquez_ap2_p1.domain.usecase

import com.example.lohammy_vasquez_ap2_p1.domain.model.Amonestacion
import com.example.lohammy_vasquez_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    suspend operator fun invoke(amonestacion: Amonestacion) = repository.upsert(amonestacion)
}