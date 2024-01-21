package com.example.ibexamen

class BaseDatosMemoria {
    companion object {
        val arregloBEquipo = arrayListOf<Equipo>()
        val arregloBJugador = arrayListOf<Jugador>()

        //-----------------Sumar el ID-----------------
        var ultimoIdEquipo = 3
        fun agregarEquipo(equipo: Equipo) {
            equipo.idEquipo = ultimoIdEquipo
            arregloBEquipo.add(equipo)
            ultimoIdEquipo++
        }

        var ultimoIdJugador = 3
        fun agregarJugador(jugador: Jugador) {
            jugador.idJugador = ultimoIdJugador
            arregloBJugador.add(jugador)
            ultimoIdJugador++
        }

        //------------------------actualizar---------------
        fun actualizarEquipo(equipo: Equipo) {
            var auxIndexEquipo = arregloBEquipo.indexOfFirst {
                it.idEquipo == equipo.idEquipo
            }
            if (auxIndexEquipo != -1) {
                arregloBEquipo[auxIndexEquipo] = equipo
            }
        }

        fun actualizarJugador(jugador: Jugador) {
            var auxIndexJugador = arregloBJugador.indexOfFirst {
                it.idJugador == jugador.idJugador
            }
            if (auxIndexJugador != -1) {
                arregloBJugador[auxIndexJugador] = jugador
            }
        }


        //-----------------------obterner datos------------------
        fun obtenerDatosEquipo(id: Int): Equipo? {
            return arregloBEquipo.firstOrNull {
                it.idEquipo == id
            }
        }

        fun obtenerDatosJugador(id: Int): Jugador? {
            return arregloBJugador.firstOrNull {
                it.idJugador== id
            }
        }

        fun obtenerEquipoJugadorId(idEquipo: Int): ArrayList<Jugador> {
            val auxArregloJugador = arrayListOf<Jugador>()
            for (jugador in arregloBJugador) {
                if (jugador.idJugador == idEquipo) {
                    auxArregloJugador.add(
                        jugador
                    )
                }
            }
            return auxArregloJugador
        }

        //----------------------eliminar----------------------------
        fun eliminarEquipo(id: Int): Boolean {
            val equipoEliminar = arregloBEquipo.find { it.idEquipo == id }

            if (equipoEliminar != null) {
                arregloBEquipo.remove(equipoEliminar)
                return true
            }
            return false

        }

        fun eliminarJugador(id: Int): Boolean {
            val jugadorEliminar = arregloBJugador.find { it.idJugador == id }

            if (jugadorEliminar != null) {
                return arregloBJugador.remove(jugadorEliminar)
            }
            return false

        }

        //-------------------------------------init------------------------
        init {
            arregloBEquipo
                .add(
                    Equipo(
                        1, "Notorious",2016 , "maxima"
                    )
               )
            arregloBEquipo
                .add(
                    Equipo(
                        2, "Atletico Madrid", 1998, "Segunda"                    )
                )


            arregloBJugador
                .add(
                    Jugador(
                        1, "Patricio Chugchila", 28, 1.74, 7,  1
                    )
                )
            /*arregloBJugador
                .add(
                    Jugador(
                        2, "Andres Bravo", 29, 1.70, 1,  1
                    )
                )*/


        }
    }
}