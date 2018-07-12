package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class UbicacionFragment : SupportMapFragment() , OnMapReadyCallback , LocationListener{

    companion object {
        private const val PERMISO_LOCATION = 1
    }
    var mapagps: GoogleMap? = null
    var localizacion : LocationManager? = null
    var marcador : Marker? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)

        return rootView
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        this.mapagps = map
        localizacion = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //mapagps!!.isMyLocationEnabled = true
                mapagps!!.uiSettings.isZoomControlsEnabled = true
                localizacion!!.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0f,this)

            } else {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISO_LOCATION)
              }
        }else{

            //mapagps!!.isMyLocationEnabled = true
            mapagps!!.uiSettings.isZoomControlsEnabled = true
        }
    }

    override fun onLocationChanged(location: Location) {
        Toast.makeText(context,"Actulizado", Toast.LENGTH_LONG).show()
        if(marcador == null){
            marcador = mapagps!!.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).draggable(true))
        }else{
            marcador?.position = LatLng(location.latitude,location.longitude)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                        //mapagps!!.isMyLocationEnabled = true
                        mapagps!!.uiSettings.isZoomControlsEnabled = true
                    }else{
                        Toast.makeText(context,"has denegado el permiso", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}



