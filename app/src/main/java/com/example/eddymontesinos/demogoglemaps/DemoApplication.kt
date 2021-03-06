package com.example.eddymontesinos.demogoglemaps

import android.app.Application
import android.arch.persistence.room.Room
import com.example.eddymontesinos.demogoglemaps.database.DemoDataBase
import com.example.eddymontesinos.demogoglemaps.database.PoblarBaseDatosCallback


class DemoApplication : Application(){

    companion object {

        var database : DemoDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, DemoDataBase::class.java,"demo_goglemaps.db")
                .addCallback(PoblarBaseDatosCallback())
                .build()
    }

}

