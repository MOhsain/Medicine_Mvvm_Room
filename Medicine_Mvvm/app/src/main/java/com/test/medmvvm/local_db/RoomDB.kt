package com.test.medmvvm.local_db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AssociatedDrug::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {
    abstract fun medDao(): MedicineDao
}