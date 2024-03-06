package com.example.proyectoiib

import java.io.Serializable

data class Pelicula(
    var nombrePelicul: String,
    var duracion: String,
    var categoria:  String,
    var fechaEstreno: Int,
    var portadaPelicula: Int,
):Serializable{

}
