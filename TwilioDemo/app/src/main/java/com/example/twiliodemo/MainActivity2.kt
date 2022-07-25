package com.example.twiliodemo

import android.Manifest
import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.twiliodemo.databinding.ActivityMain2Binding
import androidx.core.app.ActivityCompat

import android.widget.Toast

import android.content.pm.PackageManager
import android.telecom.Call
import android.util.Log

import androidx.core.content.ContextCompat
import com.twilio.video.CameraCapturer

import com.google.android.material.floatingactionbutton.FloatingActionButton

import android.view.ViewGroup

import android.widget.FrameLayout
import com.android.volley.Response
import org.chromium.base.Callback
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception


class MainActivity2 : AppCompatActivity() {

    private var mAccessToken: String? = null
    private val TAG = MainActivity::class.java.name

    /*
* Android application UI elements
*/
    private var previewFrameLayout: FrameLayout? = null
    private var localContainer: ViewGroup? = null
    private var participantContainer: ViewGroup? = null
    private val callActionFab: FloatingActionButton? = null
    private val client: OkHttpClient = OkHttpClient()

    private val accessManager: TwilioAccessManager? = null
    private val conversationsClient: ConversationsClient? = null
    private val cameraCapturer: CameraCapturer? = null

    private val conversation: Conversation? = null
    private val outgoingInvite: OutgoingInvite? = null
    private var mContext: Context? = null

    /*
* A VideoViewRenderer receives frames from a local or remote video track and renders the frames to a provided view
*/
    private val participantVideoRenderer: VideoViewRenderer? = null
    private val localVideoRenderer: VideoViewRenderer? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mContext = this.getApplicationContext()
        if (!checkPermissionForCameraAndMicrophone()) {
            requestPermissionForCameraAndMicrophone()
        }

        previewFrameLayout =  findViewById(R.id.previewFrameLayout);
        localContainer =  findViewById(R.id.localContainer);
        participantContainer =  findViewById(R.id.participantContainer);

        getCapabilityToken();
    }

    private fun getCapabilityToken() {
        try {
            run("http://[your-ngrok-url].ngrok.io/token", object : Callback() {
                fun onFailure(call: Call?, e: IOException) {
                    e.printStackTrace()
                }

                @Throws(IOException::class)
                fun onResponse(call: Call?, response: Response) {
                    try {
                        val token: String = response.body().string()
                        val obj = JSONObject(token)
                        mAccessToken = obj.getString("token")
                        Log.d(TAG, token)
                        initializeTwilioSdk(mAccessToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun run(url: String, callback: Callback): Call? {
        val request: Request = Builder()
            .url(url)
            .build()
        val response: Call = client.newCall(request)
        response.enqueue(callback)
        return response
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    private fun checkPermissionForCameraAndMicrophone(): Boolean {
        val resultCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val resultMic = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        return resultCamera == PackageManager.PERMISSION_GRANTED && resultMic == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionForCameraAndMicrophone() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.RECORD_AUDIO
            )
        ) {
            Toast.makeText(
                this,
                "Camera and Microphone permissions needed. Please allow in App Settings for additional functionality.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
                1
            )
        }
    }
}