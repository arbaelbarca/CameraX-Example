package com.arbaelbarca.cameraxfrontback

import android.Manifest
import android.content.Context
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

fun dexterPermissionMultiImage(context: Context, callback: () -> Unit) {
    Dexter.withContext(context)
        .withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    callback.invoke()
                } else showToast("Permission not completed", context)
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        })
        .onSameThread()
        .check()
}


fun showToast(message: String?, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
