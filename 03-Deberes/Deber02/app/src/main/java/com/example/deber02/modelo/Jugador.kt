package com.example.deber02.modelo

class Jugador (var idJugador: Int,
               var nombreJugador: String,
               var edad: Int,
               var estatura: Double,
               var numero: Int
               ) {
    override fun toString(): String {
        return " Nombre: ${nombreJugador}\n" + " " + "  ${edad} años\n" + " - " + "${estatura} cm\n" + " - " + "${numero}"
    }
}