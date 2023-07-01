package ir.topbest.objectdetection.utils

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ExperimentalGetImage
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ir.topbest.objectdetection.presenter.CameraPreviewActivity

@ExperimentalGetImage object CameraPermission {

    val TAG: String = CameraPreviewActivity::class.java.simpleName
    internal const val REQUEST_CODE_PERMISSIONS = 10
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    fun allPermissionsGranted(activity: AppCompatActivity) = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermissions(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            REQUIRED_PERMISSIONS,
            REQUEST_CODE_PERMISSIONS
        )
    }

}