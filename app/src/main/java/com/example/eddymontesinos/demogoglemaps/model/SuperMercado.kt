package com.example.eddymontesinos.demogoglemaps.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class SuperMercado(
@PrimaryKey(autoGenerate = true)
var id : Long? =null,
val nombre : String,
val direccion: String,
val latitud: Double,
val longitud : Double,
val fotoMiniatura : String

):Parcelable