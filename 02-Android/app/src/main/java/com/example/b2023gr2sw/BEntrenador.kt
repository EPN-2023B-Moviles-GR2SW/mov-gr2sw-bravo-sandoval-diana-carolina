package com.example.b2023gr2sw

//constructor primario
class BEntrenador (var id: Int, var nombre: String?, var descripcion: String?) {
    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }
}