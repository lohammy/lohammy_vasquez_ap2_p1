package com.example.lohammy_vasquez_ap2_p1.data.local

import android.icu.text.DecimalFormat
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Amonestaciones")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0

)


