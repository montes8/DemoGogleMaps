package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment




class UbicacionFragment : SupportMapFragment() , OnMapReadyCallback {

    var mapagps: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)




        return rootView
    }
    override fun onMapReady(map: GoogleMap?) {

        this.mapagps = map


        if(ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return
        }

        mapagps!!.isMyLocationEnabled = true
        mapagps!!.uiSettings.isZoomControlsEnabled = true



    }




}
