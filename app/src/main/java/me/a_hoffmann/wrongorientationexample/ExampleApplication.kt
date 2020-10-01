package me.a_hoffmann.wrongorientationexample

import android.app.Application
import com.microblink.MicroblinkSDK

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MicroblinkSDK.setLicenseFile("blinkId.mblic", this)
    }
}