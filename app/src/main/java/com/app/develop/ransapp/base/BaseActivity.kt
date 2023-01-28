package com.app.develop.ransapp.base

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    private var progressDialog: BaseDialog = BaseDialog()
    private var progressIniciado = false

    abstract fun initObserver()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    fun showProgressDialog() {
        if (!progressIniciado) {
            progressDialog.isCancelable = false
            progressIniciado = true
            progressDialog.show(supportFragmentManager, "")
        }
    }

    fun hideProgressDialog() {
        if (progressIniciado) {
            progressDialog.dismiss()
            progressIniciado = false
        }
    }

    fun hideKeyBoard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showMessageSnack(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .show()
    }

    fun showMessageToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
