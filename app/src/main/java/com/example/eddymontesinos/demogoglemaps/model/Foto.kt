package com.example.eddymontesinos.demogoglemaps.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [ (ForeignKey(entity = SuperMercado::class, parentColumns = arrayOf("id"), childColumns = arrayOf("idSuper"), onDelete = ForeignKey.CASCADE))])
class Foto (
    @PrimaryKey(autoGenerate = true)
    var idFoto : Long? = null,
     val idSuper : Long,
     val foto : String
    )