package com.test.medmvvm

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.room.Room

import com.test.medmvvm.local_db.AssociatedDrug
import com.test.medmvvm.local_db.MedicineDao
import com.test.medmvvm.local_db.RoomDB
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class MedicineDaoTest {

    private lateinit var db: RoomDB
    private lateinit var medicineDao: MedicineDao

    @Before
    fun createDb() {
        // Using an in-memory database for testing purposes
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RoomDB::class.java
        ).allowMainThreadQueries().build()
        medicineDao = db.medDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveDrug() = runBlocking {
        // Insert a sample drug
        val drug = AssociatedDrug(id = 1, name = "Paracetamol", dose = "500mg", strength = "500")
        medicineDao.insertDrugList(listOf(drug))

        // Retrieve the drug by its ID
        val retrievedDrug = medicineDao.getDrugById("1")
        Assert.assertNotNull(retrievedDrug)
        Assert.assertEquals("Paracetamol", retrievedDrug?.name)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndRetrieveAllDrugs() = runBlocking {
        // Insert multiple drugs
        val drug1 = AssociatedDrug(id = 1, name = "Paracetamol", dose = "500mg", strength = "500")
        val drug2 = AssociatedDrug(id = 2, name = "Ibuprofen", dose = "200mg", strength = "200")
        medicineDao.insertDrugList(listOf(drug1, drug2))

        // Retrieve all drugs
        val allDrugs = medicineDao.getAllMedicine()
        Assert.assertNotNull(allDrugs)
        Assert.assertEquals(2, allDrugs?.size)
        Assert.assertEquals("Ibuprofen", allDrugs?.get(1)?.name)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllDrugs() = runBlocking {
        // Insert multiple drugs
        val drug1 = AssociatedDrug(id = 1, name = "Paracetamol", dose = "500mg", strength = "500")
        val drug2 = AssociatedDrug(id = 2, name = "Ibuprofen", dose = "200mg", strength = "200")
        medicineDao.insertDrugList(listOf(drug1, drug2))

        // Delete all drugs
        medicineDao.deleteAllDrugs()

        // Verify the table is empty
        val allDrugs = medicineDao.getAllMedicine()
        Assert.assertTrue(allDrugs.isNullOrEmpty())
    }
}
