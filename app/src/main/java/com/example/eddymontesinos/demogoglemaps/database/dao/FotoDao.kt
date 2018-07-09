package com.example.eddymontesinos.demogoglemaps.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.eddymontesinos.demogoglemaps.model.Foto

@Dao
interface FotoDao {

    @Query("select * from Foto")
    fun litarPlatos(): List<Foto>

    @Insert
    fun insertarListaPlatos(plato : ArrayList<Foto>) : Array<Long>
}