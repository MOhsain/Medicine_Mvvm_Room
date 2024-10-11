package com.test.medmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.medmvvm.R
import com.test.medmvvm.base.MyApplication
import com.test.medmvvm.databinding.ActivityHomeBinding
import com.test.medmvvm.local_db.AssociatedDrug
import com.test.medmvvm.ui.adapter.MedicineAdapter
import com.test.medmvvm.ui.adapter.OnMedicineSelectListener
import com.test.medmvvm.utils.Debugger
import com.test.medmvvm.viewmodel.factory.HomeViewModel
import com.test.medmvvm.viewmodel.factory.ViewModelFactory
import com.ogoul.kalamtime.base.BaseActivity
import com.ogoul.kalamtime.repository.net.ApiResponse
import com.ogoul.kalamtime.repository.net.Status
import java.util.Calendar
import javax.inject.Inject

/**
 * HomeActivity is the main activity that serves as the entry point
 * for the home screen of the application. It displays user information
 * and a list of medicines in a RecyclerView.
 */
class HomeActivity : BaseActivity() {

    // View binding for the activity's layout
    lateinit var binding: ActivityHomeBinding

    // User email and password passed from the previous activity
    private var email: String? = null
    private var password: String? = null

    // Adapter for displaying the list of medicines
    private lateinit var medicineAdapter: MedicineAdapter

    // ViewModel factory for creating ViewModel instances
    @Inject
    lateinit var factory: ViewModelFactory

    // ViewModel that handles the logic and data for this activity
    private lateinit var viewModel: HomeViewModel

    /**
     * Called when the activity is first created. This method initializes
     * the activity, sets up the ViewModel, retrieves intent data, and
     * configures the RecyclerView.
     *
     * @param savedInstanceState a Bundle containing the activity's previously
     *                           saved state, if applicable.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display

        // Inflate the layout using Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        // Set up the ViewModel
        setUpViewModel()

        // Retrieve data passed from the previous activity
        getIntentData()

        // Set up the RecyclerView to display medicines
        setUpRecyclerView()
    }

    /**
     * Initializes the ViewModel and observes its LiveData properties.
     * It fetches the list of medicines from the repository.
     */
    private fun setUpViewModel() {
        MyApplication.getAppComponent(this).doInjection(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        // Observe the medicine response LiveData
        viewModel.getMedicineResponse().observe(this) {
            consumeResponse(it) // Handle the response from the API
        }

        // Fetch the list of medicines
        viewModel.getMedicinesList()
    }

    /**
     * Retrieves the email and password from the Intent that started this activity.
     * It also sets a greeting message and displays the user's email and password.
     */
    private fun getIntentData() {
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")

        binding.tvGreet.text = getGreetingMessage() // Set greeting message
        binding.tvHeader.text = "Your Email : $email\nYour Password: $password" // Display user info
    }

    /**
     * Sets up the RecyclerView with a layout manager and an adapter to display the list of medicines.
     * Also sets up an item click listener to navigate to the medicine details activity.
     */
    private fun setUpRecyclerView() {
        medicineAdapter = MedicineAdapter(arrayListOf(), OnMedicineSelectListener { item ->
            startActivity(
                Intent(this, MedDetailsActivity::class.java)
                    .putExtra("id", item.id.toString()) // Pass selected medicine ID to the details activity
            )
        })

        binding.rvMedicine.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity) // Set layout manager
            adapter = medicineAdapter // Set adapter
        }
    }

    /**
     * Generates a greeting message based on the current hour of the day.
     *
     * @return a greeting message as a String.
     */
    private fun getGreetingMessage(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        return when (currentHour) {
            in 0..11 -> "Hi, Good Morning!"
            in 12..17 -> "Hi, Good Afternoon!"
            else -> "Hi, Good Evening!"
        }
    }

    /**
     * Consumes the API response for the medicines list and updates the UI accordingly.
     *
     * @param apiResponse the API response containing the status and data or error.
     */
    private fun consumeResponse(apiResponse: ApiResponse<List<AssociatedDrug>>?) {
        when (apiResponse?.status) {

            Status.LOADING -> {
                showProgressDialog(this, isCancelable = true) // Show loading dialog
            }

            Status.SUCCESS -> {
                hideProgressDialog() // Hide loading dialog
                apiResponse.data?.let {
                    medicineAdapter.updateMedicineList(apiResponse.data) // Update adapter with new data
                }
            }

            Status.ERROR -> {
                hideProgressDialog() // Hide loading dialog
                Debugger.wtf("", "consumeResponse ERROR: " + apiResponse.error.toString()) // Log error
            }

            else -> {
                // Handle other cases if necessary
            }
        }
    }
}
