package com.app.develop.ransapp.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.app.develop.ransapp.BuildConfig
import java.io.File
import java.io.IOException


abstract class BaseActivityPhoto : BaseActivity() {
    abstract fun permissionCameraSuccess()

    private var imageFileTemporal: File? = null
    private var urlPhoto: String? = null

    companion object {
        const val REQUEST_RUNTIME_PERMISSION_CAMERA = 1000
        const val CONST_IMAGE_CAPTURE = 1001
    }

    fun requestRunTimePermissionForAccessCamera() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) !== PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA), REQUEST_RUNTIME_PERMISSION_CAMERA
            )
        } else {
            permissionCameraSuccess()
        }
    }

    fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createTakePhoto()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    this, BuildConfig.APPLICATION_ID + ".provider", photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(
                    takePictureIntent,
                    CONST_IMAGE_CAPTURE
                )
            }
        }
    }

    @Throws(IOException::class)
    private fun createTakePhoto(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile("PHOTO", ".jpg", storageDir)
        imageFileTemporal = image
        urlPhoto = image.absolutePath
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                println("${this.javaClass.name} onActivityResult Activity.RESULT_OK")
            } else {
                println("${this.javaClass.name} onActivityResult Activity.RESULT_ERROR")
                if (imageFileTemporal != null) {
                    imageFileTemporal?.delete()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RUNTIME_PERMISSION_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionCameraSuccess()
                } else {
                    val showRational: Boolean = ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.CAMERA
                    )
                    if (showRational) {
                        requestRunTimePermissionForAccessCamera()
                    } else {
                        Toast.makeText(
                            this, "Need Camera permission", Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            else -> {
            }
        }
    }

}
