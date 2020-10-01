package me.a_hoffmann.wrongorientationexample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val FIRST_SCAN = 8020
        const val SECOND_SCAN = 8021
        const val PERMISSION_REQUEST = 7020
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    startTwoScans()
                }
            }
            requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST)
        }
    }

    fun startTwoScans() {
        val intent = Intent(this, ScanActivity::class.java)
        startActivityForResult(intent, FIRST_SCAN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FIRST_SCAN && resultCode == RESULT_OK) {
            val runnable = Runnable(){
                val intent = Intent(this, ScanActivity::class.java)
                startActivityForResult(intent, SECOND_SCAN)
            }
            val handler = Handler(Looper.getMainLooper())
            handler.post(runnable)
        } else if (requestCode == SECOND_SCAN) {
            Toast.makeText(this, "Scan executed twice.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST
            -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {

                }
                return
            }
        }
    }
}