package com.example.ibexamen

class Jugador {
    var idJugador: Int
    var nombreJugador: String
    var edad: Int
    var estatura: Double
    var numero: Int
    var equipoId:Int



    constructor(
        idJugador: Int,
        nombreJugador: String,
        edad: Int,
        talla: Double,
        numero: Int,

        equipoId:Int
    ) {
        this.idJugador = idJugador
        this.nombreJugador = nombreJugador
        this.edad = edad
        this.estatura = talla
        this.numero = numero
        this.equipoId = equipoId
    }


    override fun toString(): String {
        return " Nombre: ${nombreJugador}\n" +" "+"  ${edad} años\n"+" - "+"${estatura} cm\n" +" - "+"${numero}"
    }
}