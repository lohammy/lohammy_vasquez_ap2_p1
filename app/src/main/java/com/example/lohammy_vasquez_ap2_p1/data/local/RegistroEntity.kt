package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Registros")
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true)
    val registroId: Int? = null,
    val descripcion: String = ""
)
