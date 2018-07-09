package com.example.eddymontesinos.demogoglemaps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado
import com.example.eddymontesinos.demogoglemaps.utils.DemoUtils
import kotlinx.android.synthetic.main.molde_lista_supermercados.view.*

class SupermercadoAdapter (val contexto: Context, var onDetalleClick: ((SuperMercado) -> Unit)? = null): RecyclerView.Adapter<SupermercadoAdapter.SuperMercadoViewHolder>(){

    private var listaSuperMercados : List<SuperMercado>? = null

    fun addList(listaSuperMercados : List<SuperMercado>){
        this.listaSuperMercados = listaSuperMercados

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperMercadoViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.molde_lista_supermercados,parent,false)
        return SuperMercadoViewHolder(view)
    }

    override fun getItemCount(): Int {

        val checkedUser = checkNotNull(listaSuperMercados){return 0}
        return checkedUser.size
    }

    override fun onBindViewHolder(holder: SuperMercadoViewHolder, position: Int) {
        val supermercado = listaSuperMercados!![position]


        holder.image.setImageDrawable(DemoUtils.getImage(contexto, supermercado.fotoMiniatura))
        holder.nombre.text = supermercado.nombre
        holder.direccion.text = "$/ ${supermercado.direccion}"
        holder.verDetalle.setOnClickListener{
            onDetalleClick?.invoke(supermercado)
        }

    }


    class SuperMercadoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val image = itemView.image_view_plato
        val nombre = itemView.text_nombre_supermercadoitem
        val direccion = itemView.text_direccion_item
        val verDetalle = itemView.image_detalles

    }
}