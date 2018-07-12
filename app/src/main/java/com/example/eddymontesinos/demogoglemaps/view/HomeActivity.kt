package com.example.eddymontesinos.demogoglemaps.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.eddymontesinos.demogoglemaps.ListaFragment
import com.example.eddymontesinos.demogoglemaps.MapsFragment
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.UbicacionFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var esLista = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ajusteToolbarHomes()
        cambiarFragmento(ListaFragment())
    }

    private fun ajusteToolbarHomes() {
        setSupportActionBar(homesToolbar)
        title = "SUPER MERCADOS"
    }

    fun cambiarFragmento(fragment: Fragment?){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame,fragment)
                .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


            when(item.itemId){
                R.id.action_centrocomerciales ->{
                    if(esLista){
                        item.setIcon(R.drawable.ic_lista)
                        title = "Ubicacion"
                        esLista = false
                        //mostrar mapa
                        Log.v("aaaaa", "mostrar mapa")
                        cambiarFragmento(MapsFragment())

                    }else{
                        item.setIcon(R.drawable.ic_maps)
                        title = "SUPER MERCADOS"
                        esLista = true
                        cambiarFragmento(ListaFragment())
                        //mostrar lista
                        Log.v("aaaaa", "mostrar lista")
                     }

                }

                R.id.action_ubicacion ->{

                    cambiarFragmento(UbicacionFragment())



                }
            }
             return true
    }

    fun permisoLocalizacion(){
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)

            return
        }
    }

    //comprueba si el permiso pasado por parametro esta dato en el manifiesto si es true == 0 false ==1
    fun checarpermiso(permission : String) :Boolean{
        val result =this.checkCallingOrSelfPermission(permission)
        return result == PackageManager.PERMISSION_GRANTED
    }


}
