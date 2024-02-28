package com.example.deber02.modelo

class Jugador (var idJugador: Int,
               var nombreJugador: String,
               var edad: Int,
               var estatura: Double,
               var numero: Int
               ) {
    override fun toString(): String {
        return " id: ${idJugador}\n"+ " Nombre: ${nombreJugador}\n" + " " + "  ${edad} años\n" + " Estatura: " + "${estatura} cm\n" + " Numero: " + "${numero}"
    }
}