package com.test.medmvvm.viewmodel.factory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.test.medmvvm.local_db.AssociatedDrug
import com.test.medmvvm.local_db.MedicineDao
import com.test.medmvvm.model.JsonParserModel
import com.ogoul.kalamtime.repository.net.ApiResponse
import com.otherTallguy.dagger2example.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ViewModel for managing medicine-related data.
 *
 * @property medDao DAO for accessing medicine data in the local database.
 */
class HomeViewModel @Inject constructor(private val medDao: MedicineDao) : ViewModel() {

    // LiveData object for holding the API response.
    private val responseLiveData = MutableLiveData<ApiResponse<List<AssociatedDrug>>>()

    /**
     * Returns the LiveData object containing the API response for medicine data.
     *
     * @return MutableLiveData of ApiResponse containing a list of AssociatedDrug.
     */
    fun getMedicineResponse(): MutableLiveData<ApiResponse<List<AssociatedDrug>>> {
        return responseLiveData
    }

    /**
     * Saves the list of medicines to the local database if it is empty.
     * Retrieves the list from a predefined source if needed.
     */
    fun saveMedicinesList() {
        viewModelScope.launch {
            val localDbList = medDao.getAllMedicine() // Retrieve the current list from the database
            if (localDbList?.isEmpty() == true) { // Check if the database is empty
                val list = getList() // Get the list from the predefined source
                if (list.isNotEmpty()) {
                    withContext(Dispatchers.IO) {
                        medDao.insertDrugList(list) // Insert the new list into the database
                    }
                }
            }
        }
    }

    /**
     * Retrieves the list of medicines from the local database or API and updates the LiveData object.
     * Emits loading, success, or error states based on the operation's outcome.
     */
    fun getMedicinesList() {
        responseLiveData.value = ApiResponse.loading() // Emit loading state
        viewModelScope.launch {
            try {
                val localDbList = medDao.getAllMedicine() // Retrieve the current list from the database
                if (localDbList?.isNotEmpty() == true) {
                    responseLiveData.setValue(ApiResponse.success(localDbList)) // Emit success state
                }
            } catch (e: Exception) {
                responseLiveData.setValue(ApiResponse.error(e)) // Emit error state on exception
            }
        }
    }

    /**
     * Retrieves a specific medicine item from the local database by its ID.
     *
     * @param id The ID of the medicine to retrieve.
     * @return The associated drug with the specified ID or null if not found.
     */
    suspend fun getMedicineItemById(id: String): AssociatedDrug? {
        return withContext(Dispatchers.IO) {
            medDao.getDrugById(id) // Fetch the drug from the DAO
        }
    }

    /**
     * Retrieves a predefined list of AssociatedDrug objects from JSON data.
     *
     * @return A list of AssociatedDrug objects extracted from the JSON.
     */
    private fun getList(): List<AssociatedDrug> {
        val gson = Gson() // Initialize Gson for JSON parsing
        val model: JsonParserModel = gson.fromJson(Constants.JSON, JsonParserModel::class.java) // Parse JSON
        val medList = ArrayList<AssociatedDrug>() // Initialize the list to hold medicines

        // Add associated drugs from the parsed model if available
        model.problems?.get(0)?.diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(0)?.className?.get(0)?.associatedDrug?.let {
            medList.addAll(it)
        }

        model.problems?.get(0)?.diabetes?.get(0)?.medications?.get(0)?.medicationsClasses?.get(0)?.className?.get(0)?.associatedDrug2?.let {
            medList.addAll(it)
        }
        return medList // Return the complete list
    }
}
