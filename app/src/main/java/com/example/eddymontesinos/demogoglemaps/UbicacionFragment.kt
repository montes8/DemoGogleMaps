package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


class UbicacionFragment : SupportMapFragment() , OnMapReadyCallback {


    companion object {
        private const val PERMISO_LOCATIO = 1
    }
    var mapagps: GoogleMap? = null
    var marker : Marker? = null
    var fusedLocationProviderClient : FusedLocationProviderClient? = null
    var locationrequet : LocationRequest? = null
    var locationCallback : LocationCallback? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)



        return rootView
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        this.mapagps = map


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mapagps!!.isMyLocationEnabled = true
                mapagps!!.uiSettings.isZoomControlsEnabled = true
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
                buildLocationrequest()
                buidlLocationCallback()
                fusedLocationProviderClient!!.requestLocationUpdates(locationrequet, locationCallback, Looper.myLooper())

            } else {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISO_LOCATIO)
              }
        }else{

            mapagps!!.isMyLocationEnabled = true
            mapagps!!.uiSettings.isZoomControlsEnabled = true

        }
    }


    private fun buidlLocationCallback() {
       locationCallback = object :LocationCallback(){
           override fun onLocationResult(po: LocationResult?) {
               super.onLocationResult(po)
               val location =po!!.locations.get(po.locations.size-1)
               marker = mapagps!!.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
               mapagps!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),10f))


           }
       }
    }

    private fun buildLocationrequest() {
        locationrequet = LocationRequest()
        locationrequet?.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationrequet?.interval = 5000
        locationrequet?.fastestInterval = 3000
        locationrequet?.smallestDisplacement = 10f
    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d("onRequest","onRequestPermissionsResult")
        when(requestCode){
            PERMISO_LOCATIO ->{
                Log.d("requestCode","requestCode")
                val permiso = permissions[0]
                val resultado = grantResults[0]
                if(permiso == Manifest.permission.ACCESS_FINE_LOCATION){
                    Log.d("permiso","permiso")
                    if (resultado == PackageManager.PERMISSION_GRANTED){
                        Log.d("resultado","resultado")
                        Toast.makeText(context,"has dado permiso", Toast.LENGTH_LONG).show()
                        mapagps!!.isMyLocationEnabled = true
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



