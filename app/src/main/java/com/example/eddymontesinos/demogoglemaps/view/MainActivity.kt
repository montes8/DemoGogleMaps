package com.example.eddymontesinos.demogoglemaps.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eddymontesinos.demogoglemaps.DemoApplication
import com.example.eddymontesinos.demogoglemaps.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread{
            DemoApplication.database!!.superMercadoDao().litarSuperMercados().forEach {

                Log.d("lista","${it.nombre}")
            }

        }.start()
    }
}
