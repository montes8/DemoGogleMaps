package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.eddymontesinos.demogoglemaps.view.HomeActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment




class UbicacionFragment : SupportMapFragment() , OnMapReadyCallback {

    companion object {
        private const val PERMISO_LOCATION = 1
    }
    var mapagps: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)

        return rootView
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onMapReady(map: GoogleMap?) {
        this.mapagps = map

        if (ActivityCompat.checkSelfPermission(context!!,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISO_LOCATION)
        }else{
            mapagps!!.isMyLocationEnabled = true
        }
    }



    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d("onRequest","onRequestPermissionsResult")
        when(requestCode){
            PERMISO_LOCATION ->{
                Log.d("requestCode","requestCode")
                val permiso = permissions[0]
                val resultado = grantResults[0]
                if(permiso == Manifest.permission.ACCESS_FINE_LOCATION){
                    Log.d("permiso","permiso")
                    if (resultado == PackageManager.PERMISSION_GRANTED){
                        Log.d("resultado","resultado")
                        Toast.makeText(context,"has dado permiso", Toast.LENGTH_LONG).show()
                        mapagps!!.isMyLocationEnabled = true
                    }else{
                        Toast.makeText(context,"has denegado el permiso", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}



