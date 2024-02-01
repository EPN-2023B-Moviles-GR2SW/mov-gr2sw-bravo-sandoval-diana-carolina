package com.example.deber02.modelo

import java.time.temporal.JulianFields

class Equipo(val idEquipo: Int,
             val nombre: String,
             val fecha_fundacion: Int,
             val categoria: String,
    val equipos: MutableList<Jugador>? = arrayListOf()
) {
    override fun toString(): String {
        return "${idEquipo}: ${nombre}"
    }
}