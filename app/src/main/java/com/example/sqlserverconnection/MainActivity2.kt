package com.example.sqlserverconnection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sqlserverconnection.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var mainActivity2: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity2 =
            DataBindingUtil.setContentView(this,R.layout.activity_main2)
        val intent =
            Intent(this,MainActivity::class.java)

        val text : String = intent.getStringExtra("text").toString()

        mainActivity2.textv.text = text
    }
}