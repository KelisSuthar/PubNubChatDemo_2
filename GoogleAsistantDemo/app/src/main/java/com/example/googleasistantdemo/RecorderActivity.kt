package com.example.googleasistantdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaPlayer

import android.media.MediaRecorder
import android.view.View
import com.example.googleasistantdemo.databinding.ActivityPaymentGetWaysBinding
import com.example.googleasistantdemo.databinding.ActivityRecorderBinding



class RecorderActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var mBinding: ActivityRecorderBinding
    private val mRecorder: MediaRecorder? = null

    // creating a variable for mediaplayer class
    private val mPlayer: MediaPlayer? = null

    // string variable is created for storing a file name
    private val mFileName: String? = null

    // constant for storing audio permission
    val REQUEST_AUDIO_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRecorderBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRecord->{

            }
            R.id.btnStop->{

            }
        }
    }
}