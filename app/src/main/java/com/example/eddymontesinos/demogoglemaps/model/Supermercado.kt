package com.example.eddymontesinos.demogoglemaps.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Supermercado(
@PrimaryKey(autoGenerate = true)
var id : Long? =null,
val direccion: String,
val descripcion: String,
val latitud: Double,
val longitud : Double,
val fotoMiniatura : String

)