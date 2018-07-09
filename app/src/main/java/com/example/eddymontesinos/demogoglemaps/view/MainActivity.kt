package com.example.eddymontesinos.demogoglemaps.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.example.eddymontesinos.demogoglemaps.DemoApplication
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.adapter.SuperMercadoAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var superMercadosAdapter : SuperMercadoAdapter? = null
    var handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        superMercadosAdapter = SuperMercadoAdapter(this)

        my_recyclerview.layoutManager = LinearLayoutManager(this)
        my_recyclerview.adapter = superMercadosAdapter

        Thread{
            val lista = DemoApplication.database!!.superMercadoDao().litarSuperMercados()
            handler.post {
                superMercadosAdapter!!.addList(lista)
            }
        }.start()


    }
}
