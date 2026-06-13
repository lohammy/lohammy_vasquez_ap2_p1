package com.example.lohammy_vasquez_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object AmonestacionList : Screen()

    @Serializable
    data class AmonestacionEdit(val amonestacionId: Int) : Screen()
}
