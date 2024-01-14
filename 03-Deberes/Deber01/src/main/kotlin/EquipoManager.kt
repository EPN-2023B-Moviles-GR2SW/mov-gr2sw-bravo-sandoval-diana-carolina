
import java.io.File
import java.text.SimpleDateFormat

class EquipoManager(private val archivoEquipos: String) {
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    fun guardarEquiposArchivo(equipos: List<Equipo>) {
        try {
            val archivoEquipos = File(archivoEquipos)
            archivoEquipos.bufferedWriter().use { writer ->
                equipos.forEach { equipo ->
                    with(equipo) {
                        val tieneJuvenilString = if (tieneJuvenil) "Si" else "No"

                        writer.write("Identificador Equipo: $idEquipo\n")
                        writer.write("Nombre Equipo: $nombreEquipo\n")
                        writer.write("Año de Fundacion: $AnoFundacion\n")
                        writer.write("Tiene Juvenil: $tieneJuvenilString\n")
                        writer.write("Categoria: $categoria\n")
                        writer.write("**Lista Jugadores**\n")
                        // Guardar jugadores asociados al equipo
                        listaJugadores.forEach { jugador ->
                            with(jugador) {
                                writer.write("  Identificador Jugador: $idJugador\n")
                                writer.write("  Nombre Jugador: $nombre\n")
                                writer.write("  Edad: $edad\n")
                                writer.write("  Estatura: $estatura\n")
                                writer.write("  Numero: $numero\n")
                            }
                        }


                        writer.write("\n")
                    }
                }
            }
            println("Equipos guardados correctamente.")
        } catch (ex: Exception) {
            println("Error al guardar datos en archivo de equipos.")
        }
    }
    fun cargarEquiposArchivo(): List<Equipo> {
        val equipos = mutableListOf<Equipo>()
        try {
            val archivoEquipos = File(archivoEquipos)
            if (archivoEquipos.exists()) {
                val lineasEquipos = archivoEquipos.readLines()
                var equipo: Equipo? = null
                var leyendoJugadores = false

                for (linea in lineasEquipos) {
                    val partes = linea.split(": ")
                    if (partes.size == 2) {
                        when (partes[0]) {
                            "Identificador Equipo" -> {
                                equipo?.let {
                                    equipos.add(it)
                                }
                                val idEquipo = partes[1].toInt()

                                equipo = Equipo(idEquipo, "", 0, false, "", mutableListOf())
                                leyendoJugadores = false
                            }

                            "Nombre Equipo" -> equipo?.nombreEquipo = partes[1]
                            "Año de Fundacion" -> equipo?.AnoFundacion = partes[1].toInt()
                            "Tiene Juvenil" -> equipo?.tieneJuvenil = partes[1] == "Si"
                            "Categoria" -> equipo?.categoria = partes[1]

                            "**Lista Jugadores**" -> {
                                equipo?.let {
                                    leyendoJugadores = true
                                }
                            }

                            "Identificador Jugador" -> {
                                if (leyendoJugadores) {
                                    val idJugador = partes[1].toInt()
                                    val nombre = lineasEquipos[lineasEquipos.indexOf(linea) + 1].split(": ")[1]
                                    val edad = lineasEquipos[lineasEquipos.indexOf(linea) + 2].split(": ")[1].toInt()
                                    val estatura = lineasEquipos[lineasEquipos.indexOf(linea) + 3].split(": ")[1].toDouble()
                                    val numero = lineasEquipos[lineasEquipos.indexOf(linea) + 4].split(": ")[1].toInt()
                                    equipo?.listaJugadores?.add(
                                        Jugador(idJugador, nombre, edad, estatura, numero)
                                    )
                                }
                            }
                        }
                    }
                }
                equipo?.let {
                    equipos.add(it)
                }
            }
        } catch (ex: Exception) {
            println("Error al cargar datos desde archivo de equipos.")
        }
        return equipos
    }

    /*
        fun cargarEquiposArchivo(): List<Equipo> {
            val equipos = mutableListOf<Equipo>()
            try {
                val archivoEquipos = File(archivoEquipos)
                if (archivoEquipos.exists()) {
                    val lineasEquipos = archivoEquipos.readLines()
                    var equipo: Equipo? = null

                    for (linea in lineasEquipos) {
                        val partes = linea.split(": ")
                        if (partes.size == 2) {
                            when (partes[0]) {
                                "Identificador Equipo" -> {
                                    equipo?.let {
                                        equipos.add(it)
                                    }
                                    val idEquipo = partes[1].toInt()

                                    equipo = Equipo(idEquipo, "", 0, false, "", mutableListOf())
                                }

                                "Nombre Equipo" -> equipo?.nombreEquipo = partes[1]
                                "Año de Fundacion" -> equipo?.AnoFundacion = partes[1].toInt()
                                "Tiene Juvenil" -> equipo?.tieneJuvenil = partes[1] == "Si"
                                "Categoria" -> equipo?.categoria = partes[1]

                                "Identificador Jugador" -> {
                                    val idJugador = partes[1].toInt()
                                    val nombre = lineasEquipos[lineasEquipos.indexOf(linea) + 1].split(": ")[1]
                                    val edad = lineasEquipos[lineasEquipos.indexOf(linea) + 2].split(": ")[1].toInt()
                                    val estatura = lineasEquipos[lineasEquipos.indexOf(linea) + 3].split(": ")[1].toDouble()
                                    val numero = lineasEquipos[lineasEquipos.indexOf(linea) + 4].split(": ")[1].toInt()
                                    equipo?.listaJugadores?.add(
                                        Jugador(idJugador, nombre, edad, estatura, numero)
                                    )
                                }
                            }
                        }
                    }
                    equipo?.let {
                        equipos.add(it)
                    }
                }
            } catch (ex: Exception) {
                println("Error al cargar datos desde archivo de equipos.")
            }
            return equipos
        }*/


}


