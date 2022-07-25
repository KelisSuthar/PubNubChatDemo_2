package com.example.googleasistantdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.googleasistantdemo.databinding.ActivityMainBinding
import androidx.core.app.ActivityCompat

import android.os.Build
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    intent.handleIntent()
//        Handler().postDelayed({startActivity(Intent(this@MainActivity,MainActivity2::class.java))}, 1500)
        Handler().postDelayed({startActivity(Intent(this@MainActivity,PaymentGetWays::class.java))}, 1500)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }
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

    override fun onClick(v: View?) {

    }
    private fun Intent.handleIntent(){
        when(action){
            Intent.ACTION_VIEW->{
                handleDeepLink(data)
            }
        }
    }

    private fun handleDeepLink(data: Uri?) {
        when(data?.path)
        {
            "/open"->{
                val type = data.getQueryParameter("FeatureEntitySet").orEmpty()
                redirect(type)
            }
        }
    }

    private fun redirect(type: String) {
        when(type){
            "Trip"->{
             startActivity(Intent(this,MainActivity2::class.java))
            }
            "Profile"->{
                Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show()
            }
        }
    }
}