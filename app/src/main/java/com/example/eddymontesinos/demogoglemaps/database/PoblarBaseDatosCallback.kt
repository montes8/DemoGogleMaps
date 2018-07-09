package com.example.eddymontesinos.demogoglemaps.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.util.Log
import com.example.eddymontesinos.demogoglemaps.DemoApplication
import com.example.eddymontesinos.demogoglemaps.model.Foto
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado

class PoblarBaseDatosCallback:  RoomDatabase.Callback(){

    //se ejecuta despues que se hayan creado las tablas.
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)


        Log.i("db", "onCreate()")

        val supermercado= SuperMercado(nombre = "Mega Plaza",direccion =  "Alfreto Mendiola 3698,Independencia",latitud = -11.99405732,longitud = -77.06241231, fotoMiniatura = "megaplaza")
        val supermercadodos = SuperMercado(nombre = "Royal Plaza",direccion = "Av.Carlos izaguirre 287-289,Independencia",latitud = -11.99041933 ,longitud = -77.06290144, fotoMiniatura = "royal")
        val superMercadotres = SuperMercado(nombre = "Plaza Norte",direccion =  "Tomas Valle,Cercado de Lima 15311",latitud = -12.00681565,longitud = -77.05884285, fotoMiniatura = "plazanorte")
        val superMercadocuatro = SuperMercado(nombre = "Real Plaza",direccion =  "Av.Inca Garcilaso de la Vega 1337,Cercado de Lima ",latitud = -12.05688609,longitud = -77.03773475, fotoMiniatura = "realplaza")
        val superMercadocinco = SuperMercado(nombre = "Rambla",direccion =  "Av.Brasil 702,Breña",latitud = -12.06631014,longitud = -77.04746379, fotoMiniatura = "rambla")

        Thread {
            val idmega=DemoApplication.database!!.superMercadoDao().insertarSupermercados(supermercado)
            val idroyal=DemoApplication.database!!.superMercadoDao().insertarSupermercados(supermercadodos)
            val idplaza=DemoApplication.database!!.superMercadoDao().insertarSupermercados(superMercadotres)
            val idreal=DemoApplication.database!!.superMercadoDao().insertarSupermercados(superMercadocuatro)
            val idrambla=DemoApplication.database!!.superMercadoDao().insertarSupermercados(superMercadocinco)

            Log.i("db", "idmega = $idmega")
            Log.i("db", "idroyal = $idroyal")
            Log.i("db", "idplaza = $idplaza")
            Log.i("db", "idreal = $idreal")
            Log.i("db", "idrambla = $idrambla")

            val listaFotos = ArrayList<Foto>()
            listaFotos.add(Foto(idSuper = idmega,foto = "megaa"))
            listaFotos.add(Foto(idSuper = idmega,foto = "megab"))
            listaFotos.add(Foto(idSuper = idmega,foto = "megac"))
            listaFotos.add(Foto(idSuper = idroyal,foto = "royala"))
            listaFotos.add(Foto(idSuper = idroyal,foto = "royalb"))
            listaFotos.add(Foto(idSuper = idplaza,foto = "plazanortea"))
            listaFotos.add(Foto(idSuper = idplaza,foto = "plazanorteb"))
            listaFotos.add(Foto(idSuper = idplaza,foto = "plazanortec"))
            listaFotos.add(Foto(idSuper = idplaza,foto = "plazanorted"))
            listaFotos.add(Foto(idSuper = idplaza,foto = "plazanortee"))
            listaFotos.add(Foto(idSuper = idreal,foto = "realplazaa"))
            listaFotos.add(Foto(idSuper = idreal,foto = "realplazab"))
            listaFotos.add(Foto(idSuper = idreal,foto = "realplazac"))
            listaFotos.add(Foto(idSuper = idreal,foto = "realplazad"))
            listaFotos.add(Foto(idSuper = idrambla,foto = "ramblaa"))
            listaFotos.add(Foto(idSuper = idrambla,foto = "ramblac"))
            listaFotos.add(Foto(idSuper = idrambla,foto = "ramblaj"))

            DemoApplication.database!!.fotoDao().insertarListaFotos(listaFotos)
        }.start()


    }

}