package com.example.deber02.database

class BaseDeDatos {
    var tablaEquipo : SqliteHelperEquipo?=null
    var tablaJugador : SqliteHelperJugador?= null
    var equipoSeleccionado = Equipo(0,"",0,"", arrayListOf())
    var jugadorSeleccionado = Jugador(0,"",0,0.0,0)
}