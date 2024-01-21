package com.example.ibexamen

class Equipo {
    var idEquipo: Int?
    var nombreEquipo: String

    var fechaFundacion: Int
    var categoria: String

    constructor(
        idEquipo: Int? = null,
        nombreEquipo: String,

        fechaFundacion: Int,
       categoria: String
    ) {
        this.idEquipo = idEquipo
        this.nombreEquipo = nombreEquipo

        this.fechaFundacion = fechaFundacion
        this.categoria = categoria
    }

    override fun toString(): String {
        return "${idEquipo}: ${nombreEquipo}"
    }
}