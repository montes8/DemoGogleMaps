package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
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
        val marcador : MarkerOptions?
        val peru = LatLng(-11.927467033443245,-76.99462369988953)
        //añadimos la posicion del marcador y le pasamos el titulo
         //mapa?.addMarker(MarkerOptions().position(peru).title("PERU.Lima"))
        marcador = MarkerOptions()
        marcador.position(peru)
        marcador.title("PERU.Lima")
        marcador.snippet("Super Mercado")
        marcador.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_edit_location_black_24px))
        mapa?.addMarker(marcador)

        val camara = CameraPosition.Builder()
                .target(peru)
                .zoom(5f)//
                .bearing(0f)//rotacion que se puede dar ala camara 0 a maximo de 365grados
                .tilt(0f)//angulo de nuestro marcador 0 a 90 grados
                .build()
        mapa?.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
        //movemos la camara al ñpunto del marcador
         //mapa?.moveCamera(CameraUpdateFactory.newLatLng(peru))

    }


}
