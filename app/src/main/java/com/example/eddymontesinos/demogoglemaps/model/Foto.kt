package com.example.eddymontesinos.demogoglemaps.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.example.eddymontesinos.demogoglemaps.model.Supermercado

@Entity(foreignKeys = [ (ForeignKey(entity = Supermercado::class, parentColumns = arrayOf("id"), childColumns = arrayOf("idSuper"), onDelete = ForeignKey.CASCADE))])
class Foto (
    @PrimaryKey(autoGenerate = true)
    var id : Long? =null,
     val idSuper : Long? = null,
     val foto : String = ""
    )