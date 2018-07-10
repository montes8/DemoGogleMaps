package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demogoglemaps.model.Mercado
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.os.Handler
import android.support.v7.content.res.AppCompatResources
import com.example.eddymontesinos.demogoglemaps.view.DetalleActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import org.jetbrains.anko.support.v4.startActivity


class MapsFragment: SupportMapFragment() ,OnMapReadyCallback {

    var mapa: GoogleMap? = null
    var handler : Handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)


        return rootView
    }

    override fun onMapReady( map: GoogleMap?) {
        this.mapa = map


        val lima = LatLng(-11.97650551576034,-77.05710844110047)
        mapa!!.uiSettings.isZoomControlsEnabled = true
        mapa!!.uiSettings.isCompassEnabled= true
        //añadimos la posicion del marcador y le pasamos el titulo
         //mapa?.addMarker(MarkerOptions().position(lima).title("PERU.Lima"))// se gregaga para k sea arrastrable isDraggable(true)

        val builder = LatLngBounds.Builder()

        Thread{
            val lista = DemoApplication.database!!.superMercadoDao().litarSuperMercados()

            handler.post {

                lista.forEach {

                    val marker = mapa?.addMarker(MarkerOptions().position(LatLng(it.latitud,it.longitud)).title(it.nombre))
                    //marker?.tag = it
                    builder.include(marker?.position)

                    val bounds : LatLngBounds= builder.build()
                    val padding = 200 // offset from edges of the map in pixels
                    val cu :CameraUpdate= CameraUpdateFactory.newLatLngBounds(bounds,padding)
                    mapa?.moveCamera(cu)
                }
            }
        }.start()
/*
          mapa?.setOnMarkerClickListener(object :GoogleMap.OnMarkerClickListener{
              override fun onMarkerClick(p0: Marker?): Boolean {
                  startActivity<DetalleActivity>()
                  return true
              }

          })*/
      /* val camara = CameraPosition.Builder()
                .target(lima)
                .zoom(10f)//vista deacuerdo a pais
                .bearing(0f)//rotacion que se puede dar ala camara 0 a maximo de 365grados
                .tilt(0f)//angulo de nuestro marcador 0 a 90 grados
                .build()*/
        //mapa?.animateCamera(CameraUpdateFactory.newCameraPosition(camara))

        //movemos la camara al ñpunto del marcador
       // mapa?.moveCamera(CameraUpdateFactory.newLatLng(lima))


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
