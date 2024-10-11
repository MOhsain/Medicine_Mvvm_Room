package com.test.medmvvm.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.medmvvm.R
import com.test.medmvvm.base.MyApplication
import com.test.medmvvm.databinding.ActivityMedicineDetailsBinding
import com.test.medmvvm.viewmodel.factory.HomeViewModel
import com.test.medmvvm.viewmodel.factory.ViewModelFactory
import com.ogoul.kalamtime.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * MedDetailsActivity displays the details of a specific medicine item.
 * It retrieves the medicine ID from the intent and fetches the corresponding
 * medicine details using the ViewModel. The details are then displayed
 * on the screen.
 */
class MedDetailsActivity : BaseActivity() {

    // View binding for the activity's layout
    lateinit var binding: ActivityMedicineDetailsBinding

    // The ID of the medicine item to be displayed
    private var id: String? = null

    // ViewModel factory for creating ViewModel instances
    @Inject
    lateinit var factory: ViewModelFactory

    // ViewModel that manages the UI-related data for this activity
    lateinit var viewModel: HomeViewModel

    /**
     * Called when the activity is first created. This method initializes
     * the activity, sets up the ViewModel, and retrieves the medicine ID
     * from the intent to display its details.
     *
     * @param savedInstanceState a Bundle containing the activity's previously
     *                           saved state, if applicable.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display

        // Inflate the layout using Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_medicine_details)

        // Set up the ViewModel for this activity
        setUpViewModel()

        // Retrieve the medicine ID from the intent
        getIntentData()
    }

    /**
     * Initializes the ViewModel for this activity. It sets up dependency injection
     * and retrieves the ViewModel instance for managing the UI-related data.
     */
    private fun setUpViewModel() {
        MyApplication.getAppComponent(this).doInjection(this) // Inject dependencies
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java) // Get ViewModel instance
    }

    /**
     * Retrieves the medicine ID from the intent, fetches the corresponding
     * medicine details from the ViewModel, and updates the UI with the
     * fetched data.
     */
    private fun getIntentData() {
        // Get the medicine ID passed via the intent
        id = intent.getStringExtra("id")

        // Log the retrieved ID for debugging purposes
        Log.wtf("MedId", "$id")

        // Launch a coroutine to fetch medicine details
        lifecycleScope.launch {
            val model = viewModel.getMedicineItemById(id.toString()) // Fetch medicine details
            withContext(Dispatchers.Main) {
                // Update the UI with the medicine details
                binding.tvName.text = "Name: ${model?.name}"
                binding.tvDose.text = "Dose: ${model?.dose}" // Fixed: should refer to dose instead of name
                binding.tvStrength.text = "Strength: ${model?.strength}"
            }
        }
    }
}
