package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        this.mapagps = map



        mapagps!!.isMyLocationEnabled = true





    }

    fun permisoLocalizacion(){
        if (ActivityCompat.checkSelfPermission(context!!,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(context as Activity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISO_LOCATION)

            return
        }
    }
}



