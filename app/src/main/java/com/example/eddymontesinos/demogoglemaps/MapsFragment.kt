package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapsFragment : Fragment() ,OnMapReadyCallback{
    override fun onMapReady(p0: GoogleMap?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


}
