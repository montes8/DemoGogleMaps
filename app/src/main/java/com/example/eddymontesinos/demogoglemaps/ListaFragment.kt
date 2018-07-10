package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demogoglemaps.adapter.SuperMercadoAdapter


class ListaFragment : Fragment() {

    var myVista : View?= null

    var superMercadosAdapter : SuperMercadoAdapter? = null
    var handler : Handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        myVista =inflater.inflate(R.layout.fragment_lista, container, false)

        val reciclerView = myVista!!.findViewById<RecyclerView>(R.id.my_recyclerview)

        superMercadosAdapter = SuperMercadoAdapter(context!!.applicationContext)

        reciclerView.layoutManager = LinearLayoutManager(context!!.applicationContext)
        reciclerView.adapter = superMercadosAdapter

                Thread{
                    val lista = DemoApplication.database!!.superMercadoDao().litarSuperMercados()
                    handler.post {
                        superMercadosAdapter!!.addList(lista)
                    }
                }.start()

        return myVista
    }







}
