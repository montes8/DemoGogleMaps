package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsFragment : Fragment() ,OnMapReadyCallback{

    val mapFragment = SupportMapFragment()
    var myVista : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        myVista = inflater.inflate(R.layout.fragment_maps, container, false)

        return myVista
    }

    override fun onMapReady(p0: GoogleMap?) {

    }
}
