package com.example.deber02.database

import com.example.deber02.modelo.Equipo
import com.example.deber02.modelo.Jugador

class BaseDeDatos {
    companion object{
    var tablaEquipo : SqliteHelperEquipo?=null
    var tablaJugador : SqliteHelperJugador?= null
    var equipoSeleccionado = Equipo(0,"",0,"", arrayListOf())
    var jugadorSeleccionado = Jugador(0,"",0,0.0,0)
    }
    }