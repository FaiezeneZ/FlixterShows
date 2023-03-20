package com.example.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Debug", "Main 1")
        val supportFragmentManager = supportFragmentManager
        Log.i("Debug", "Main 2")
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        Log.i("Debug", "Main 3")
        fragmentTransaction.replace(androidx.appcompat.R.id.content, FragmentTvShows(), null).commit()
        Log.i("Debug", "Main 4")
    }
}