package ir.topbest.objectdetection.presenter

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import ir.topbest.objectdetection.utils.CameraPermission
import ir.topbest.objectdetection.utils.TextReaderAnalyzer
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import ir.topbest.objectdetection.R

@ExperimentalGetImage
@AndroidEntryPoint
class CameraPreviewActivity : AppCompatActivity() {

    lateinit var fragment : MainFragment

    private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }
    private val imageAnalyzer by lazy {
        ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
            .also {
                it.setAnalyzer(
                    cameraExecutor,
                    TextReaderAnalyzer {text-> fragment.onTextFound(text) }
                )
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_preview)
        fragment= supportFragmentManager.findFragmentById(R.id.fragmentContainer) as MainFragment

        if (CameraPermission.allPermissionsGranted(this)) {
            startCamera()
        } else {
            CameraPermission.requestPermissions(this)
        }
    }



    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            {
                val preview = Preview.Builder()
                    .build()
                    .also { it.setSurfaceProvider(fragment.getCameraPreview.surfaceProvider) }
                cameraProviderFuture.get().bind(preview, imageAnalyzer)
            },
            ContextCompat.getMainExecutor(this)
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CameraPermission.REQUEST_CODE_PERMISSIONS) {
            if (CameraPermission.allPermissionsGranted(this)) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun ProcessCameraProvider.bind(
        preview: Preview,
        imageAnalyzer: ImageAnalysis
    ) = try {
        unbindAll()
        bindToLifecycle(
            this@CameraPreviewActivity,
            CameraSelector.DEFAULT_BACK_CAMERA,
            preview,
            imageAnalyzer
        )
    } catch (ise: IllegalStateException) {
        // Thrown if binding is not done from the main thread
        Log.e(ContentValues.TAG, "Binding failed", ise)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}