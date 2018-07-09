package com.example.eddymontesinos.demogoglemaps.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.example.eddymontesinos.demogoglemaps.DemoApplication
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import com.example.eddymontesinos.demosqlite_romm.DemoApplication
import com.example.eddymontesinos.demosqlite_romm.R
import com.example.eddymontesinos.demosqlite_romm.model.Plato

class PoblarBaseDatosCallback(val context: Context):  RoomDatabase.Callback(){

    //se ejecuta despues que se hayan creado las tablas.
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Log.i("db", "onCreate()")
        //poner inserts

        val listaSuperMercado = ArrayList<SuperMercado>()

        listaSuperMercado.add(SuperMercado(nombre = "Mega Plaza",direccion =  15.50,calorias = "1200 kl",descuento = 10, fotoMinitura = "megaplaza"))
        listaSuperMercado.add(SuperMercado(nombre = "Royal Plaza",direccion =  10.50,calorias = "1200 kl",descuento = 10, imagen = "royal"))
        listaSuperMercado.add(SuperMercado(nombre = "Plaza Norte",direccion =  20.50,calorias = "1200 kl",descuento = 10, imagen = "plazanorte"))
        listaSuperMercado.add(SuperMercado(nombre = "Real Plaza",direccion =  17.00,calorias = "1200 kl",descuento = 10, imagen = "realplaza"))
        listaSuperMercado.add(SuperMercado(nombre = "Rambla",direccion =  17.00,calorias = "1200 kl",descuento = 10, imagen = "rambla"))


        Thread {
            DemoApplication.database!!.superMercadoDao().insertarListaPlatos()
        }.start()
    }

}