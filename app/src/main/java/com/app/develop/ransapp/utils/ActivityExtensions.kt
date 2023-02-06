package com.app.develop.ransapp.utils

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

fun AppCompatActivity?.registerObserverActivityResult(onResult: (ActivityResult) -> Unit): ActivityResultLauncher<Intent> {
    return (this as FragmentActivity).registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        onResult(result)
    }
}