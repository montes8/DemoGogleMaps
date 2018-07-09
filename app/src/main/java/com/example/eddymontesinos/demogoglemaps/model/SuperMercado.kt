package com.example.eddymontesinos.demogoglemaps.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class SuperMercado(
@PrimaryKey(autoGenerate = true)
var id : Long? =null,
val nombre : String,
val direccion: String,
val latitud: Double,
val longitud : Double,
val fotoMiniatura : String

)