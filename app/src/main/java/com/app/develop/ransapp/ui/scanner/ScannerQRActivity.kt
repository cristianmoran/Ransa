package com.app.develop.ransapp.ui.scanner

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.app.develop.ransapp.base.BaseActivity
import com.app.develop.ransapp.databinding.ActivityScannerQractivityBinding
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

class ScannerQRActivity : BaseActivity() {

    lateinit var binding: ActivityScannerQractivityBinding
    private var mCameraSource: CameraSource? = null
    private var barcodeDetector: BarcodeDetector? = null
    private var scanDataComplete: Boolean = false
    private val viewModel: ScannerViewModel by (this as FragmentActivity).viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerQractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpQRScanner()
    }

    override fun initObserver() {
        //TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ScannerQRActivity::class.java)
        }

        private val TAG: String = ScannerQRActivity::class.java.simpleName
        val BUNDLE_CODE_SCANNED: String = "CODE_SCANNED"
    }

    private fun setUpQRScanner() {
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()
        mCameraSource = CameraSource.Builder(this, barcodeDetector)
            .setAutoFocusEnabled(true)
            .build()
        Log.d(TAG, "+---------> setUpQRScanner <---------+")
        binding.surfaceView.holder?.addCallback(object : SurfaceHolder.Callback {
            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    Log.d(TAG, "+---------> surfaceCreated <---------+")
                    Log.d(TAG, "+---------> start <---------+")
                    mCameraSource?.start(binding.surfaceView.holder)
                } catch (ie: IOException) {
                    Log.e("CAMERA SOURCE", ie.message!!)
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                mCameraSource?.stop()
            }
        })
        barcodeDetector?.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}
            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes: SparseArray<Barcode> = detections.getDetectedItems()
                if (barcodes.size() != 0) {
                    Log.d(TAG, barcodes.valueAt(0).displayValue)
                    binding.surfaceView.post {
                        try {
                            val scannedValue: String = barcodes.valueAt(0).displayValue
                            onScannedComplete(scannedValue)
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                }
            }
        })
    }

    private fun onScannedComplete(scannedData: String) {
        if (!scanDataComplete) {
            scanDataComplete = true
            val intent = Intent()
            intent.putExtra(BUNDLE_CODE_SCANNED, scannedData)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}