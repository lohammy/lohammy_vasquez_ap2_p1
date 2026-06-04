package com.example.lohammy_vasquez_ap2_p1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegistroEntity::class], version = 1, exportSchema = false)
abstract class RegistroDb : RoomDatabase() {
    abstract fun registroDao(): RegistroDao
}
