package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment: SupportMapFragment() ,OnMapReadyCallback {

    var mapa: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        getMapAsync(this)

        return rootView
    }

    override fun onMapReady( map: GoogleMap?) {
        this.mapa = map
        val peru = LatLng(-11.927467033443245,-76.99462369988953)
         mapa?.addMarker(MarkerOptions().position(peru).title("PERU.Lima"))
        mapa?.moveCamera(CameraUpdateFactory.newLatLng(peru))

    }


}
