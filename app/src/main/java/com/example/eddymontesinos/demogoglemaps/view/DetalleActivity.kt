package com.example.eddymontesinos.demogoglemaps.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.eddymontesinos.demogoglemaps.R

class DetalleActivity : AppCompatActivity() {


    companion object {
        const val SUPERMERCADO_PARAM = "supermercado"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
    }
}
