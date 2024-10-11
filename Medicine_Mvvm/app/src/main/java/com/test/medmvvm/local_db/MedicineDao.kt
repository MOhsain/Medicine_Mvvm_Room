package com.test.medmvvm.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugList(drug: List<AssociatedDrug>)

    // Get a drug by its name from the medicine table
    @Query("SELECT * FROM medicine WHERE id = :id")
    suspend fun getDrugById(id: String): AssociatedDrug?

    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicine(): List<AssociatedDrug>?

    // Delete all drugs from the medicine table
    @Query("DELETE FROM medicine")
    suspend fun deleteAllDrugs()
}

