package com.example.eddymontesinos.demogoglemaps


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demogoglemaps.model.Mercado
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.support.v7.content.res.AppCompatResources
import com.google.android.gms.maps.model.BitmapDescriptor



class MapsFragment: SupportMapFragment() ,OnMapReadyCallback {

    var mapa: GoogleMap? = null
    var listasupermercados : ArrayList<Mercado>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)

         listasupermercados = ArrayList<Mercado>()
        listasupermercados?.add(Mercado("Royal Plaza",-11.99041933,-77.06290144))
        listasupermercados?.add(Mercado("Mega Plaza",-11.99405732,-77.06241231))
        listasupermercados?.add(Mercado("Plaza Norte",-12.00681565,-77.05884285))
        listasupermercados?.add(Mercado("Real Plaza",-12.05688609,-77.03773475))
        listasupermercados?.add(Mercado("Ramba Brasil",-12.06631014,-77.04746379))


        return rootView
    }

    override fun onMapReady( map: GoogleMap?) {
        this.mapa = map
        val marcador : MarkerOptions?
        val superIcono = getBitmapDescriptor(R.drawable.ic_shopping)
        val peru = LatLng(-11.927467033443245,-76.99462369988953)
        //añadimos la posicion del marcador y le pasamos el titulo
         mapa?.addMarker(MarkerOptions().position(peru).title("PERU.Lima"))// se gregaga para k sea arrastrable isDraggable(true)
        //personalñizandomarcador customisando
    /*    marcador = MarkerOptions()
        marcador.position(peru)
        marcador.title("PERU.Lima")
        marcador.snippet("Super Mercado")/cuadro de subtitulo o descripcion
        marcador.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.arrow_down_float))
        mapa?.addMarker(marcador)*/

      listasupermercados?.forEach {
          mapa?.addMarker(MarkerOptions().position(LatLng(it.latitud,it.longitud))
                  .title(it.nombre)
                  .icon(superIcono))
      }
        val camara = CameraPosition.Builder()
                .target(peru)
                .zoom(5f)//vista deacuerdo a pais
                .bearing(0f)//rotacion que se puede dar ala camara 0 a maximo de 365grados
                .tilt(0f)
                //angulo de nuestro marcador 0 a 90 grados
                .build()
        mapa?.animateCamera(CameraUpdateFactory.newCameraPosition(camara))
        //movemos la camara al ñpunto del marcador
         //mapa?.moveCamera(CameraUpdateFactory.newLatLng(peru))

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
