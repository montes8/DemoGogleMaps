package com.example.eddymontesinos.demogoglemaps.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.eddymontesinos.demogoglemaps.database.dao.FotoDao
import com.example.eddymontesinos.demogoglemaps.database.dao.SuperMercadoDao
import com.example.eddymontesinos.demogoglemaps.model.Foto
import com.example.eddymontesinos.demogoglemaps.model.SuperMercado

@Database(entities = [SuperMercado::class, Foto::class],version = 1)
abstract class DemoDataBase : RoomDatabase(){

    abstract fun superMercadoDao() : SuperMercadoDao
    abstract fun fotoDao() : FotoDao
}