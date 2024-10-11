package com.ogoul.kalamtime.base

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.test.medmvvm.R


open class BaseActivity : AppCompatActivity() {

    private var progressDialog: AlertDialog.Builder? = null
    private var alertDialog: AlertDialog? = null

    fun showProgressDialog(context: Context, text: String = "", isCancelable: Boolean = false) {
        progressDialog = AlertDialog.Builder(context)
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        val view: View = layoutInflater.inflate(R.layout.progress_dialoge, viewGroup, false)
        progressDialog?.setView(view)

        if (text.isNotEmpty()) {
            val textView: TextView = view.findViewById(R.id.tvTitle)
            textView.text = text
        }
        alertDialog = progressDialog?.create()
        alertDialog?.setCancelable(isCancelable)
        alertDialog?.show()
    }

    fun hideProgressDialog() {
        try {
            if (alertDialog != null) {
                if (alertDialog?.isShowing == true) {
                    alertDialog?.dismiss()
                }
            }
        } catch (e: Exception) {

        }
    }

}