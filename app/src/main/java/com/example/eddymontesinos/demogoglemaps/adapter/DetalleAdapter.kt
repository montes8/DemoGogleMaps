package com.example.eddymontesinos.demogoglemaps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demogoglemaps.R
import com.example.eddymontesinos.demogoglemaps.model.Foto
import com.example.eddymontesinos.demogoglemaps.utils.DemoUtils
import kotlinx.android.synthetic.main.molde_detalle.view.*

class DetalleAdapter (val contexto: Context): RecyclerView.Adapter<DetalleAdapter.DetalleViewHolder>(){

    private var listaFotos : List<Foto>? = null

    fun addList(listaFotos : List<Foto>){
        this.listaFotos = listaFotos

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetalleViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.molde_detalle,parent,false)
        return DetalleViewHolder(view)
    }

    override fun getItemCount(): Int {

        val checkedFoto = checkNotNull(listaFotos){return 0}
        return checkedFoto.size
    }

    override fun onBindViewHolder(holder: DetalleViewHolder, position: Int) {
        val foto = listaFotos!![position]


        holder.foto.setImageDrawable(DemoUtils.getImage(contexto, foto.foto))

    }
    class DetalleViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

        val foto = itemView.imagen_super


    }
}