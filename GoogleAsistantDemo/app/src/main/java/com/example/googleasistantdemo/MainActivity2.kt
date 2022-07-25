package com.example.googleasistantdemo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.opengl.Matrix
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.googleasistantdemo.databinding.ActivityMain2Binding
import com.example.googleasistantdemo.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.*
import java.util.ArrayList
import java.util.Locale
import androidx.core.app.ActivityCompat.startActivityForResult

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    lateinit var mBinding: ActivityMain2Binding
    var adapter: CounterAdaptor? = null
    val ENG_LANG = "eng"
    val HINDI_LANG = "hi"
    var arrayList = ArrayList<String>()
    var PLAY = true
    lateinit var speechRecognizerIntent :Intent
    lateinit var  speechRecognizer :SpeechRecognizer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.btn.setOnClickListener(this)
        arrayList.add("Android    = 1")
        arrayList.add("IOS        = 1")
        arrayList.add("SCO        = 1")
        arrayList.add("PHP        = 1")
        arrayList.add("LARAVEL    = 1")


        chnageLang("hi")
        mBinding.rv.adapter = null
        adapter = CounterAdaptor(this)
        mBinding.rv.adapter = adapter

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
//        recording()


    }

    private fun googleRecorder() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            "hi"
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

        try {
            startActivityForResult(intent, 1)
        } catch (e: Exception) {
            Toast
                .makeText(
                    this@MainActivity2, " " + e.message,
                    Toast.LENGTH_SHORT
                )
                .show()
        }
    }

    private fun recording() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        );
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(error: Int) {

            }

            override fun onResults(results: Bundle?) {
                val data: ArrayList<String> =
                    results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
                Toast.makeText(this@MainActivity2, data.first(), Toast.LENGTH_SHORT).show()
            }

            override fun onPartialResults(partialResults: Bundle?) {

            }

            override fun onEvent(eventType: Int, params: Bundle?) {

            }

        })

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn -> {
//                if(PLAY)
//                {
//                    PLAY = false
//                    mBinding.btn.text = "Recording"
//                    speechRecognizer.startListening(speechRecognizerIntent);
//                }else{
//                    speechRecognizer.stopListening();
//                    mBinding.btn.text = "Record"
//                    PLAY = true
//                }
                googleRecorder()
//                Toast.makeText(this, PLAY.toString(), Toast.LENGTH_SHORT).show()

            }
        }

    }

    private fun chnageLang(Lang: String) {
        val locale = Locale(Lang)
        val config = Configuration(resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
         data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )
                mBinding.txt.setText(
                    Objects.requireNonNull(result)?.get(0)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy();
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                1010
            )
        }
    }


    inner class CounterAdaptor(var activity: Activity) :
        RecyclerView.Adapter<CounterAdaptor.myViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): CounterAdaptor.myViewHolder {
            return return myViewHolder(
                LayoutInflater.from(activity).inflate(R.layout.row_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: CounterAdaptor.myViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        inner class myViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var textView: TextView = itemView.findViewById(R.id.txt)
            fun bind(position: Int) {
                textView.text = arrayList[position]
            }
        }
    }
}
