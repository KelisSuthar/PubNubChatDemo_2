package com.example.twiliovideocallingdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.twilio.video.*
import java.util.*
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.koushikdutta.ion.Ion
import com.twilio.audioswitch.AudioDevice
import com.twilio.audioswitch.AudioSwitch

import com.twilio.video.RemoteParticipant

import com.twilio.video.LocalParticipant


class MainActivity : AppCompatActivity() {

    private val CAMERA_MIC_PERMISSION_REQUEST_CODE = 1
    private val CAMERA_PERMISSION_INDEX = 0
    private val MIC_PERMISSION_INDEX = 1

    var room: Room? = null
    var videoView: VideoView? = null
    var btn: Button? = null
    private var localAudioTrack: LocalAudioTrack? = null
    private var localVideoTrack: LocalVideoTrack? = null
    private var accessToken = ""


    private val audioSwitch by lazy {
        AudioSwitch(
            applicationContext, preferredDeviceList = listOf(
                AudioDevice.BluetoothHeadset::class.java,
                AudioDevice.WiredHeadset::class.java, AudioDevice.Speakerphone::class.java, AudioDevice.Earpiece::class.java
            )
        )
    }
    private val TWILIO_ACCESS_TOKEN = "abcdef0123456789"
    private val ACCESS_TOKEN_SERVER = "http://localhost:3000"
    private val audioCodec: AudioCodec
        get() {
            val audioCodecName = "audio"

            return when (audioCodecName) {
                IsacCodec.NAME -> IsacCodec()
                OpusCodec.NAME -> OpusCodec()
                PcmaCodec.NAME -> PcmaCodec()
                PcmuCodec.NAME -> PcmuCodec()
                G722Codec.NAME -> G722Codec()
                else -> OpusCodec()
            }
        }
    private val videoCodec: VideoCodec
        get() {
            val videoCodecName = "video"

            return when (videoCodecName) {
                H264Codec.NAME -> H264Codec()
                Vp9Codec.NAME -> Vp9Codec()
                else -> Vp8Codec()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermissionForCameraAndMicrophone()) {
            requestPermissionForCameraMicrophoneAndBluetooth()
        } else {
//            audioSwitch.start { audioDevices, audioDevice -> updateAudioDeviceIcon(audioDevice) }
        }
        videoView = findViewById(R.id.videoview)
        btn = findViewById(R.id.btn)
        setAccessToken()
    }
    private fun requestPermissionForCameraMicrophoneAndBluetooth() {
        val permissionsList: Array<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )
        }
        requestPermissions(permissionsList)
    }
//    private fun updateAudioDeviceIcon(selectedAudioDevice: AudioDevice?) {
//        val audioDeviceMenuIcon = when (selectedAudioDevice) {
//            is AudioDevice.BluetoothHeadset -> R.drawable.ic_bluetooth_white_24dp
//            is AudioDevice.WiredHeadset -> R.drawable.ic_headset_mic_white_24dp
//            is AudioDevice.Speakerphone -> R.drawable.ic_volume_up_white_24dp
//            else -> R.drawable.ic_phonelink_ring_white_24dp
//        }
//
//        audioDeviceMenuItem?.setIcon(audioDeviceMenuIcon)
//    }
    private fun requestPermissions(permissions: Array<String>) {
        var displayRational = false
        for (permission in permissions) {
            displayRational =
                displayRational or ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    permission
                )
        }
        if (displayRational) {
            Toast.makeText(this, "Please Add Permisstions", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(
                this, permissions, CAMERA_MIC_PERMISSION_REQUEST_CODE)
        }
    }
    private fun checkPermissionForCameraAndMicrophone(): Boolean {
        return checkPermissions(
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO))
    }


    private fun checkPermissions(permissions: Array<String>): Boolean {
        var shouldCheck = true
        for (permission in permissions) {
            shouldCheck = shouldCheck and (PackageManager.PERMISSION_GRANTED ==
                    ContextCompat.checkSelfPermission(this, permission))
        }
        return shouldCheck
    }

    override fun onDestroy() {
        super.onDestroy()
        localAudioTrack?.release()
        localVideoTrack?.release()
    }
    private fun setAccessToken() {

            this.accessToken = TWILIO_ACCESS_TOKEN

            retrieveAccessTokenfromServer()

    }

    private fun retrieveAccessTokenfromServer() {
        Ion.with(this)
            .load("$ACCESS_TOKEN_SERVER?identity=${UUID.randomUUID()}")
            .asString()
            .setCallback { e, token ->
                if (e == null) {
                    this@MainActivity.accessToken = token
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error retrieving token", Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
    }
    fun connectToRoom(roomName: String?) {
        val connectOptions = ConnectOptions.Builder(accessToken)
            .roomName(roomName!!)
            .audioTracks(listOf(localAudioTrack))
            .videoTracks(listOf(localVideoTrack))
//            .dataTracks(localDataTracks)
            .build()
        room = Video.connect(this, connectOptions, object : Room.Listener {
            override fun onConnected(room: Room) {

            }

            override fun onConnectFailure(room: Room, twilioException: TwilioException) {

            }

            override fun onReconnecting(room: Room, twilioException: TwilioException) {

            }

            override fun onReconnected(room: Room) {

            }

            override fun onDisconnected(room: Room, twilioException: TwilioException?) {

            }

            override fun onParticipantConnected(room: Room, remoteParticipant: RemoteParticipant) {

            }

            override fun onParticipantDisconnected(
                room: Room,
                remoteParticipant: RemoteParticipant
            ) {

            }

            override fun onRecordingStarted(room: Room) {

            }

            override fun onRecordingStopped(room: Room) {
            }

        })
        // After receiving the connected callback the LocalParticipant becomes available
        val localParticipant = room!!.localParticipant
        Log.i("LocalParticipant ", localParticipant!!.identity)

// Get the first participant from the room

        val participant = room!!.remoteParticipants[0]
        Log.i("HandleParticipants", participant.identity + " is in the room.")
    }


}