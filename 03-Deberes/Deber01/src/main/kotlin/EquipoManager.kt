
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
/*
    fun guardarJugadorArchivo(jugador: Jugador) {
        try {
            val archivoJugadores = File(archivoJugadores)
            archivoJugadores.bufferedWriter().use { writer ->
                with(jugador) {
                    writer.write("Identificador Jugador: $idJugador\n")
                    writer.write("Nombre Jugador: $nombre\n")
                    writer.write("Edad: $edad\n")
                    writer.write("Estatura: $estatura\n")
                    writer.write("Numero: $numero\n")
                }
            }
        } catch (ex: Exception) {
            println("Error al guardar datos en archivo de jugadores.")
        }
    }*/

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
    }
}
/*
    fun cargarJugadoresArchivo(): List<Jugador> {
        val jugadores = mutableListOf<Jugador>()
        try {
            val archivoJugadores = File(archivoJugadores)
            if (archivoJugadores.exists()) {
                val lineasEquipos = archivoJugadores.readLines()
                var jugador: Jugador? = null

                for (linea in lineasEquipos) {
                    val partes = linea.split(": ")
                    if (partes.size == 2) {
                        when (partes[0]) {
                            "Identificador Equipo" -> {
                                jugador?.let {
                                    jugadores.add(it)
                                }
                                val idJugador = partes[1].toInt()

                                jugador = Jugador(idJugador, "", 0, 0.0,0)
                            }
                            "Nombre Jugador" -> jugador?.nombre =partes[1]
                            "Edad" -> jugador?.edad = partes[1].toInt()
                            "Estatura" -> jugador?.estatura = partes[1].toDouble()
                            "Categoria" -> equipo?.categoria=partes[1]

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
    }

}*/

/*class EquipoManager( private val archivoEquipos: String){
    private val dateFormat = SimpleDateFormat("\"EEE MMM dd HH:mm:ss zzz yyyy\", Locale.ENGLISH")

    fun guardarEquiposArchivo(equipos : List<Equipo>){
        try {
            val archivoEquipos = File(archivoEquipos)
            FileWriter(archivoEquipos).use { writer ->
                writer.write("***Equipos Guardados***")
                equipos.forEach { equipo ->
                    with(equipo) {
                        val tieneJuvenilString = if (tieneJuvenil) "Si" else "No"
                        val fechaFormateada = dateFormat.format(fechaFundacion)
                        writer.write("Identificador Equipo: $idEquipo\n")
                        writer.write("Nombre Equipo: $nombreEquipo\n")

                        writer.write("Fecha de Fundacion: $fechaFormateada\n")
                        writer.write("Tiene Juvenil: $tieneJuvenilString\n")
                        writer.write("Categoria: $categoria\n")
                    }
                }
                writer.write("\n")
            }


            /*archivoEquipos.bufferedWriter().use { writer ->
                equipos.forEach { equipo ->
                    with(equipo) {

                        writer.write("Nombre Equipo: $nombreEquipo\n")
                        writer.write("Fecha de Fundacion: $fechaFundacion\n")
                        writer.write("Tiene Juvenil: ${if (tieneJuvenil) "Si" else "No"}\n")
                        writer.write("Categoria: $categoria\n")

                        listaJugadores.forEach { jugador ->
                            with(jugador) {
                                writer.write("Identificador Jugador: $idJugador\n")
                                writer.write("Nombre Jugador: $nombre\n")
                                writer.write("Edad: $edad\n")
                                writer.write("Estatura: $estatura\n")
                                writer.write("Numero: $numero\n")
*/



            println("Datos guardados")
        }catch (ex: Exception) {
            println("Error  guardar  datos en  archivo de texto.")
        }
    }
    fun cargarEquiposArchivo(): List<Equipo> {
        val equipos = mutableListOf<Equipo>()
        try {
            val archivoEquipos = File(archivoEquipos)
            if (archivoEquipos.exists()) {
                val txtString = archivoEquipos.readLines()

                var equipo: Equipo? = null

                for (txt in txtString) {
                    val separar = txt.split(": ")
                    if (separar.size == 2) {
                        when (separar[0]) {
                            "Identificador Equipo" -> {
                                equipo?.let {
                                    equipos.add(it)
                                }
                                val nombreEquipo = separar[1]
                                equipo = Equipo(0, nombreEquipo, Date(), false, "", mutableListOf())
                            }
                            //Se usa un parse para pasar a int lo que se necesite
                            "Identificador Equipo" -> equipo?.idEquipo = separar[1].toInt()
                            "Fecha de Fundacion" -> {
                                try {
                                    val fechaFundacion = dateFormat.parse(separar[1])
                                    equipo?.fechaFundacion = fechaFundacion
                                } catch (e: Exception) {
                                    println("Error al parsear la fecha de fundación: ${e.message}")
                                }
                            }

                            "Tiene Juvenil" -> equipo?.tieneJuvenil = separar[1] == "Si"
                            "Identificador Jugador" -> {
                                val idJugador = separar[1].toInt()
                                val nombre = txtString[txtString.indexOf(txt) + 1].split(": ")[1]
                                val edad = txtString[txtString.indexOf(txt) + 2].split(": ")[1].toInt()
                                val estatura = txtString[txtString.indexOf(txt) + 3].split(": ")[1].toDouble()
                                val numero = txtString[txtString.indexOf(txt) + 4].split(": ")[1].toInt()
                                equipo?.listaJugadores?.add(
                                    Jugador(idJugador, nombre, edad, estatura, numero)
                                )
                            }
                        }
                    }
                    equipo?.let {
                        equipos.add(it)
                    }
                }
            }
        } catch (ex: Exception) {
            println("Error  cargar  datos en  archivo de texto.")
        }


        return equipos
    }




    private fun equipoExiste(idEquipo: Int): Boolean {
        val equiposExistentes = cargarEquiposArchivo()
        return equiposExistentes.any { it.idEquipo == idEquipo }
    }
    fun listarEquipos() {
        val equipos = cargarEquiposArchivo()

        if (equipos.isNotEmpty()) {
            println("Equipos registrados:")
            equipos.forEach { equipo ->
                println("**********************************")
                println("Identificador Equipo: ${equipo.idEquipo}")
                println("Nombre Equipo: ${equipo.nombreEquipo}")


                if (equipo.listaJugadores.isNotEmpty()) {
                    println("Jugadores:")
                    equipo.listaJugadores.forEach { jugador ->
                        println("   Identificador Jugador: ${jugador.idJugador}")
                        println("   Nombre Jugador: ${jugador.nombre}")
                        println("   Edad: ${jugador.edad}")
                        println("   Estatura: ${jugador.estatura}")
                        println("   Numero: ${jugador.numero}")
                        println()
                    }
                } else {
                    println("Este equipo no tiene jugadores.")
                }
            }
        } else {
            println("No hay equipos registrados.")
        }
    }



    }

/*

    fun crearEquipo(equipo: Unit){
        equipo.getquipo = equipos.size + 1
        equipos.add(equipo)
        guardarEquipos()
    }

    fun leerEquipo(idEquipo: Int):Equipo?{
        return equipos.find { it.idEquipo==idEquipo }
    }

    fun actualizarEquipo(equipo: Equipo){
        val indice=equipos.indexOfFirst {
            it.idEquipo == equipo.idEquipo}
            if (indice != -1) {
                equipos[indice] = equipo
                guardarEquipos()
            }
        }
    fun eliminarEquipo(idEquipo: Int){
        equipos.removeAll{it.idEquipo == idEquipo}
        guardarEquipos()
    }

    fun listarEquipos():List<Equipo>{
        return equipos.toList()
    }*/



*/


