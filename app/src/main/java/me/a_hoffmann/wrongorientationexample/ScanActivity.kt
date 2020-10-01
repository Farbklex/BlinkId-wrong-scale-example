package me.a_hoffmann.wrongorientationexample

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microblink.entities.recognizers.Recognizer
import com.microblink.entities.recognizers.RecognizerBundle
import com.microblink.entities.recognizers.blinkid.generic.BlinkIdRecognizer
import com.microblink.entities.recognizers.successframe.SuccessFrameGrabberRecognizer
import com.microblink.hardware.orientation.Orientation
import com.microblink.view.CameraEventsListener
import com.microblink.view.recognition.ScanResultListener
import kotlinx.android.synthetic.main.activity_scan.*


class ScanActivity : AppCompatActivity(), CameraEventsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val recognizer = SuccessFrameGrabberRecognizer(BlinkIdRecognizer())
        val bundle = RecognizerBundle(recognizer)
        recognizerView.recognizerBundle = bundle;
        recognizerView.initialOrientation = Orientation.ORIENTATION_LANDSCAPE_RIGHT
        recognizerView.setOrientationAllowedListener { true }

        val scanResultListener = ScanResultListener {
            val result: BlinkIdRecognizer.Result = recognizer.slaveRecognizer.result as BlinkIdRecognizer.Result
            if (result.resultState == Recognizer.Result.State.Valid) {

            }

            val immutableCopy = result.clone()
            recognizerView.resetRecognitionState()
            recognizerView.pauseScanning()

            setResult(RESULT_OK)
            finish()
        }

        recognizerView.setScanResultListener(scanResultListener)
        recognizerView.cameraEventsListener = this
        recognizerView.setLifecycle(this.lifecycle)

    }

    override fun onAutofocusFailed() {

    }

    override fun onAutofocusStarted(p0: Array<out Rect>?) {

    }

    override fun onAutofocusStopped(p0: Array<out Rect>?) {

    }

    override fun onCameraPreviewStarted() {

    }

    override fun onCameraPreviewStopped() {

    }

    override fun onError(p0: Throwable) {

    }

    override fun onCameraPermissionDenied() {

    }
}