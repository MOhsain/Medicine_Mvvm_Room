package com.test.medmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.medmvvm.R
import com.test.medmvvm.base.MyApplication
import com.test.medmvvm.databinding.ActivityLoginBinding
import com.test.medmvvm.viewmodel.factory.HomeViewModel
import com.test.medmvvm.viewmodel.factory.ViewModelFactory
import javax.inject.Inject

/**
 * LoginActivity is responsible for handling user login functionality.
 * It collects the username and password from the user, and upon login,
 * it navigates to the HomeActivity while passing the user's credentials.
 */
class LoginActivity : AppCompatActivity() {

    // View binding for the activity's layout
    lateinit var binding: ActivityLoginBinding

    // ViewModel factory for creating ViewModel instances
    @Inject
    lateinit var factory: ViewModelFactory

    // ViewModel that manages the UI-related data for this activity
    private lateinit var viewModel: HomeViewModel

    /**
     * Called when the activity is first created. This method initializes
     * the activity, sets up the ViewModel, and sets an OnClickListener
     * on the login button to handle user login.
     *
     * @param savedInstanceState a Bundle containing the activity's previously
     *                           saved state, if applicable.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enables edge-to-edge display

        // Inflate the layout using Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Set up the ViewModel for this activity
        setUpViewModel()

        // Set up the login button click listener
        binding.loginButton.setOnClickListener {
            // Retrieve the username and password from the EditText fields
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Navigate to HomeActivity with the user's credentials
            startActivity(
                Intent(this, HomeActivity::class.java)
                    .putExtra("email", username) // Pass the username as email
                    .putExtra("password", password) // Pass the password
            )

            // Finish this activity so the user cannot navigate back to it
            finish()
        }
    }

    /**
     * Initializes the ViewModel for this activity. It sets up dependency injection
     * and retrieves the ViewModel instance for managing the UI-related data.
     */
    private fun setUpViewModel() {
        MyApplication.getAppComponent(this).doInjection(this) // Inject dependencies
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java] // Get ViewModel instance

        // Save the medicines list in the ViewModel
        viewModel.saveMedicinesList()
    }
}
