package com.example.eddymontesinos.demogoglemaps


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v7.content.res.AppCompatResources
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import com.example.eddymontesinos.demogoglemaps.utils.DemoUtils
import com.example.eddymontesinos.demogoglemaps.view.DetalleActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import org.jetbrains.anko.support.v4.toast

class MapsFragment: SupportMapFragment() ,OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener ,GoogleMap.InfoWindowAdapter{
    companion object {
        private const val PERMISO_LOCATION = 1
    }

    var mapa: GoogleMap? = null
    var handler : Handler = Handler()
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

    override fun onMapReady( map: GoogleMap?) {
        this.mapa = map
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                mapa!!.uiSettings.isZoomControlsEnabled = true
                mapa!!.setOnInfoWindowClickListener(this)

                mapa!!.setInfoWindowAdapter(this)
                mapa!!.uiSettings.isCompassEnabled= true

                val builder = LatLngBounds.Builder()
                Thread{
                    val lista = DemoApplication.database!!.superMercadoDao().litarSuperMercados()
                    handler.post {
                        lista.forEach {
                            marker = mapa?.addMarker(MarkerOptions().position(LatLng(it.latitud,it.longitud)).title(it.nombre))
                            marker?.tag = it

                            builder.include(marker?.position)
                            val bounds : LatLngBounds= builder.build()
                            val padding = 200 // offset from edges of the map in pixels
                            val cu :CameraUpdate= CameraUpdateFactory.newLatLngBounds(bounds,padding)
                            mapa?.moveCamera(cu)
                        }
                    }
                }.start()

                buildLocationrequest()
                buidlLocationCallback()
                fusedLocationProviderClient!!.requestLocationUpdates(locationrequet, locationCallback, Looper.myLooper())

            } else {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),PERMISO_LOCATION)
            }
        }else{


            mapa!!.uiSettings.isZoomControlsEnabled = true

        }







    }

    override fun onInfoWindowClick(p0: Marker?) {
        val supermarket = p0?.tag as SuperMercado
            val intent = Intent(context, DetalleActivity::class.java)
            intent.putExtra(DetalleActivity.SUPERMERCADO_MAPS,supermarket)
            startActivity(intent)


    }

    override fun getInfoContents(info: Marker?): View {
        val view = layoutInflater.inflate(R.layout.molde_dialog_maps,null)

        val tvnombre = view.findViewById<TextView>(R.id.nombre_mercado_maps)
        val tvdireccion = view.findViewById<TextView>(R.id.direccion_mercado_maps)
        val tvImage = view.findViewById<ImageView>(R.id.imagen_maps)

        try {
            val supers : SuperMercado = info?.tag as SuperMercado

            tvImage.setImageDrawable(DemoUtils.getImage(context!!,supers.fotoMiniatura))
            tvnombre.text = supers.nombre
            tvdireccion.text =  supers.direccion
        }catch (e:TypeCastException){
            e.printStackTrace()
        }

        return view
    }

    override fun getInfoWindow(p0: Marker) :View?{
      return null
    }

    private fun buidlLocationCallback() {
        locationCallback = object :LocationCallback(){
            override fun onLocationResult(po: LocationResult?) {
                super.onLocationResult(po)
                val location =po!!.locations.get(po.locations.size-1)

                marker = mapa!!.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Aqui Estoy"))
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
            PERMISO_LOCATION ->{
                Log.d("requestCode","requestCode")
                val permiso = permissions[0]
                val resultado = grantResults[0]
                if(permiso == Manifest.permission.ACCESS_FINE_LOCATION){
                    Log.d("permiso","permiso")
                    if (resultado == PackageManager.PERMISSION_GRANTED){
                        Log.d("resultado","resultado")
                        Toast.makeText(context,"has dado permiso", Toast.LENGTH_LONG).show()
                        mapa!!.uiSettings.isZoomControlsEnabled = true
                    }else{
                        Toast.makeText(context,"has denegado el permiso", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    private fun getBitmapDescriptor(id: Int): BitmapDescriptor {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val vectorDrawable = AppCompatResources.getDrawable(context!!, id) as VectorDrawable

            val h = vectorDrawable.intrinsicHeight
            val w = vectorDrawable.intrinsicWidth

            vectorDrawable.setBounds(0, 0, w, h)

            val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bm)
            vectorDrawable.draw(canvas)

            return BitmapDescriptorFactory.fromBitmap(bm)

        } else {
            return BitmapDescriptorFactory.fromResource(id)
        }
    }
}
