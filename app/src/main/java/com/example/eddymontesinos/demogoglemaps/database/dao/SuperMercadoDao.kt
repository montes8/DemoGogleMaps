package com.example.eddymontesinos.demogoglemaps.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado

@Dao
interface SuperMercadoDao {

    @Query("select * from SuperMercado")
    fun litarSuperMercados(): List<SuperMercado>

    @Insert
    fun insertarListaSupermercados(superMercado : ArrayList<SuperMercado>) : Array<Long>
    @Insert
    fun insertarSupermercados(superMercado :SuperMercado) : Long
}