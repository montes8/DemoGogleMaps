package com.example.eddymontesinos.demogoglemaps


import android.content.Intent
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
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import com.example.eddymontesinos.demogoglemaps.utils.DemoUtils
import com.example.eddymontesinos.demogoglemaps.view.DetalleActivity
import com.example.eddymontesinos.demogoglemaps.view.PruebaActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import kotlinx.android.synthetic.main.abc_activity_chooser_view.view.*
import kotlinx.android.synthetic.main.molde_dialog_maps.*
import org.jetbrains.anko.support.v4.startActivity


class MapsFragment: SupportMapFragment() ,OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener ,GoogleMap.InfoWindowAdapter{


    var mapa: GoogleMap? = null
    var handler : Handler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)




        return rootView
    }

    override fun onMapReady( map: GoogleMap?) {
        this.mapa = map

        mapa!!.uiSettings.isZoomControlsEnabled = true
        mapa!!.setOnInfoWindowClickListener(this)
        mapa!!.setInfoWindowAdapter(this)
        mapa!!.uiSettings.isCompassEnabled= true

        val builder = LatLngBounds.Builder()

        Thread{
            val lista = DemoApplication.database!!.superMercadoDao().litarSuperMercados()

            handler.post {

                val hashMap = HashMap<Marker,SuperMercado>()
                var contador =0
                lista.forEach {
                    val marker = mapa?.addMarker(MarkerOptions().position(LatLng(it.latitud,it.longitud)).title(it.nombre))
                    marker?.tag = it

                    hashMap.put(marker!!,it)

                    builder.include(marker?.position)
                    val bounds : LatLngBounds= builder.build()
                    val padding = 200 // offset from edges of the map in pixels
                    val cu :CameraUpdate= CameraUpdateFactory.newLatLngBounds(bounds,padding)
                    mapa?.moveCamera(cu)
                }
            }
        }.start()



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

        val supers : SuperMercado = info?.tag as SuperMercado
        tvImage.setImageDrawable(DemoUtils.getImage(context!!,supers.fotoMiniatura))
        tvnombre.text = supers.nombre
        tvdireccion.text =  supers.direccion

        return view
    }

    override fun getInfoWindow(p0: Marker) :View?{
      return null
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
