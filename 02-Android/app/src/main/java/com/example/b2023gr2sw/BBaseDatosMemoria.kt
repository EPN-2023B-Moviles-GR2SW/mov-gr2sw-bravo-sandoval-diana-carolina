package com.example.b2023gr2sw

class BBaseDatosMemoria {
    companion object {
        //arreglo de entrenadores
        //el companionObjetc para inicializar con el "init"

        val arregloBEntrenador = arrayListOf<BEntrenador>()
        //en este bloque deseamos inicializar
        init {
            arregloBEntrenador.add(BEntrenador(1,"Adrian","a@a.com"))
            arregloBEntrenador.add(BEntrenador(2,"Vicente","b@b.com"))
            arregloBEntrenador.add(BEntrenador(3,"Carolina","c@c.com"))
        }
    }
}