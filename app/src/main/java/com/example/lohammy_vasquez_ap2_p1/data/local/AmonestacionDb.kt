package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AmonestacionEntity::class], version = 1, exportSchema = false)
abstract class AmonestacionDb : RoomDatabase() {
    abstract fun amonestacionDao(): AmonestacionDao
}
