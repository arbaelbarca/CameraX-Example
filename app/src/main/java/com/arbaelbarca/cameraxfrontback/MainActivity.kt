package com.arbaelbarca.cameraxfrontback

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.arbaelbarca.cameraxfrontback.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    var getFileIdCard: File? = null

    val launcherIntent = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == 200) {
            val myFile = it.data?.getSerializableExtra("file") as File
            getFileIdCard = myFile
            val getBitmap = BitmapFactory.decodeFile(myFile.path)
            bindingMain.imgCameraX.setImageBitmap(getBitmap)
        }
    }

    val bindingMain: ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initial()
    }

    private fun initial() {
        bindingMain.btnCameraX.setOnClickListener {
            dexterPermissionMultiImage(this) {
                val i = Intent(this, CameraXActivity::class.java)
                launcherIntent.launch(i)
            }
        }
    }
}