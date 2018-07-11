package com.example.eddymontesinos.demogoglemaps.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import com.example.eddymontesinos.demogoglemaps.DemoApplication
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.adapter.DetalleAdapter
import com.example.eddymontesinos.demogoglemaps.adapter.SuperMercadoAdapter
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import kotlinx.android.synthetic.main.activity_detalle.*
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.SnapHelper
import android.widget.Toast


class DetalleActivity : AppCompatActivity() {
    var fotoAdapter : DetalleAdapter? = null
    var handler : Handler = Handler()

    companion object {
        const val SUPERMERCADO_PARAM = "supermercado"
        const val SUPERMERCADO_MAPS = "supermercadodesdemapa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        ajustarTollbarDetalle()
        val detallesSuperMercado = intent.getParcelableExtra<SuperMercado>(SUPERMERCADO_PARAM)
        val detalleMaps = intent.getParcelableExtra<SuperMercado>(SUPERMERCADO_MAPS)


        detalle_recyclerview.layoutManager = LinearLayoutManager(this@DetalleActivity, LinearLayout.HORIZONTAL, false)
        fotoAdapter = DetalleAdapter(this@DetalleActivity)
        detalle_recyclerview.adapter = fotoAdapter

        val nombre = findViewById<TextView>(R.id.text_detail_nombre)
        val latitud = findViewById<TextView>(R.id.text_detail_latitude)
        val longitud = findViewById<TextView>(R.id.text_detail_longitude)
        val direccion = findViewById<TextView>(R.id.text_detail_direccion)

        if (detallesSuperMercado != null){
                text_detail_nombre.text = detallesSuperMercado.nombre
                text_detail_latitude.text = detallesSuperMercado.latitud.toString()
                text_detail_longitude.text = detallesSuperMercado.longitud.toString()
                text_detail_direccion.text = detallesSuperMercado.direccion



                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(detalle_recyclerview)

                Thread {
                    val lista = DemoApplication.database!!.fotoDao().verFotoSupermercado(detallesSuperMercado.id!!.toLong())
                    handler.post {
                        fotoAdapter!!.addList(lista)
                    }
                }.start()
        }else{

            text_detail_nombre.text = detalleMaps.nombre
            text_detail_latitude.text = detalleMaps.latitud.toString()
            text_detail_longitude.text = detalleMaps.longitud.toString()
            text_detail_direccion.text = detalleMaps.direccion



            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(detalle_recyclerview)

            Thread {
                val lista = DemoApplication.database!!.fotoDao().verFotoSupermercado(detalleMaps.id!!.toLong())
                handler.post {
                    fotoAdapter!!.addList(lista)
                }
            }.start()
        }
    }


    fun ajustarTollbarDetalle(){
        setSupportActionBar(detalleToolbar)
        title = "Detalles"
        detalleToolbar.navigationIcon = getDrawable(R.drawable.ic_atras)
        detalleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
