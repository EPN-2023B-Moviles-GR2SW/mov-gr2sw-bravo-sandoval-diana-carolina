class CRUD(private val equiposNuevos: MutableList<Equipo>, private val gestionarArchivos: EquipoManager) {

    fun ListarEquipos() {
        if (equiposNuevos.isEmpty()) {
            println("*************************************************************************")
            println("*******No existen Equipos.********")
            println("**************************************************************************")
            return
        }else {
            println("**********************************")
            println("Equipos Registrados:")

            equiposNuevos.forEachIndexed { index, equipo ->
                println(" ID: ${equipo.idEquipo}, Nombre: ${equipo.nombreEquipo}")
            }
        }
        println("**********************************")
    }

    fun ActualizarEquipo(idSeleccionado: Int) {

        println("Eliga el Equipo para actualizar")
        ListarEquipos()


        if (idSeleccionado != null) {
            val equipoEncontrado = buscarEquipoId(equiposNuevos, idSeleccionado)

            if (equipoEncontrado != null) {
                //val equipoSeleccionado = equipoEncontrado
                println("**********   Datos del Equipo: ${equipoEncontrado.nombreEquipo} **************************")

                println("Ingrese el nombre del equipo: ")
                equipoEncontrado.nombreEquipo = readLine() ?: equipoEncontrado.nombreEquipo

                println("¿Su equipo cuenta con un juvenil? (Si/No): ")
                val tieneJuvenilString = readLine()?.toLowerCase() ?: equipoEncontrado.tieneJuvenil
                val tieneJuvenil = when (tieneJuvenilString) {
                    "si", "SI", "Si" -> true
                    "no" -> false
                    else -> false
                }
                equipoEncontrado.tieneJuvenil = tieneJuvenil
                println("Nueva Categoria del equipo: (Segunda,Primera,Maxima)")
                equipoEncontrado.categoria = readLine() ?: equipoEncontrado.categoria

                gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
                println("************Empleado actualizado************")
                /*println("""
                            ID: ${equipoEncontrado.idEquipo}
                            Nombre: ${equipoEncontrado.nombreEquipo}
                            Año Fundacion: ${equipoEncontrado.AnoFundacion}
                            Tiene Juvenil: ${if(equipoEncontrado.tieneJuvenil) "Si" else "No"}
                            Categoria: ${equipoEncontrado.categoria}
                        """)*/

            } else {
                println("No se encontró ningun equipo con el ID: $idSeleccionado")
            }
        }

    }

    fun listarEquiposJugadores(idSeleccionado: Int) {
        /*print("Ingrese el codigo del equipo: ")
        val idSeleccionado = readLine()?.toIntOrNull() ?: 0*/
        if(idSeleccionado != null) {
            val equipoEncontrado = buscarEquipoId(equiposNuevos, idSeleccionado)
            if (equipoEncontrado != null) {
                println("Lista de jugadores del equipo ${equipoEncontrado.nombreEquipo}:")
                if (equipoEncontrado.listaJugadores.isNotEmpty()) {
                    equipoEncontrado.listaJugadores.forEachIndexed { index, jugador ->
                        println("${index + 1}, ID Jugador: ${jugador.idJugador}, Nombre Jugador: ${jugador.nombre}, Edad: ${jugador.edad}, Estatura: ${jugador.estatura}, Número: ${jugador.numero}")
                    }
                } else {
                    println("El equipo no tiene jugadores registrados.")
                }
            } else {
                println("No se encontró un equipo con el ID proporcionado.")
            }
        }
    }


    fun crearEquipo() {
        /*println("Ingrese el codigo del equipo")
        val idEquipo = readLine()?.toIntOrNull() ?: -1*/

        println("Ingrese el nombre del equipo: ")
        val nombreEquipo = readLine().orEmpty()
        print("Año de Fundación: ")
        val anoFundacion = readLine()?.toIntOrNull() ?: 0

        println("¿Su equipo cuenta con un juvenil? (Si/No): ")
        val tieneJuvenilString = readLine()?.toLowerCase()
        val tieneJuvenil = when (tieneJuvenilString) {
            "si", "SI", "Si" -> true
            "no" -> false
            else -> false
        }
        println("Categoria del equipo: (Segunda,Primera,Maxima)")
        val categoria = readLine().orEmpty()
        val nuevoEquipo = Equipo(idEquipo = generarId(equiposNuevos), nombreEquipo, anoFundacion, tieneJuvenil, categoria)
        equiposNuevos.add(nuevoEquipo)
        gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
    }

    fun equipoSelecionar() {
        print("Ingrese el codigo del equipo: ")
        val idSeleccionado = readLine()?.toIntOrNull() ?: 0

        if (idSeleccionado != null) {
            val equipoEncontrado = buscarEquipoId(equiposNuevos, idSeleccionado)

            if (equipoEncontrado != null) {
                println("**********   Datos del Equipo: ${equipoEncontrado.nombreEquipo} **************************")
                println(
                    "ID: ${equipoEncontrado.idEquipo}, Nombre:" +
                            "${equipoEncontrado.nombreEquipo}, Año Fundacion: ${equipoEncontrado.AnoFundacion}," +
                            "Tiene Juvenil: ${if (equipoEncontrado.tieneJuvenil) "Si" else "No"}, Categoria:" +
                            "${equipoEncontrado.categoria}"
                )
                println("*****************************************************************************************")
                /*println("""
                            ID: ${equipoEncontrado.idEquipo}
                            Nombre: ${equipoEncontrado.nombreEquipo}
                            Año Fundacion: ${equipoEncontrado.AnoFundacion}
                            Tiene Juvenil: ${if(equipoEncontrado.tieneJuvenil) "Si" else "No"}
                            Categoria: ${equipoEncontrado.categoria}
                        """)*/

            } else {
                println("No se encontró ningun equipo con el ID: $idSeleccionado")
            }
        }
    }

    fun buscarEquipoId(equipos: List<Equipo>, id: Int): Equipo? {
        return equipos.find { it.idEquipo == id }
    }
    fun obtenerEquipoPorId(idEquipo: Int): Equipo? {
        // Buscar el equipo por ID
        val equipoEncontrado = buscarEquipoId(equiposNuevos, idEquipo)

        return equipoEncontrado
    }

    fun eliminarEquipo(id: Int){
        val equipo = equiposNuevos.find { it.idEquipo==id }
        if(equipo!=null){
            equiposNuevos.remove(equipo)
            println("Equipo Eliminado:  ${equipo.nombreEquipo}")
            gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
        }else{
            println("El equipo con ID $id no existe")
        }

    }

    fun eliminarJugador(idEquipo: Int){
        ListarEquipos()
        val equipo = equiposNuevos.find{it.idEquipo==idEquipo}
        listarEquiposJugadores(idEquipo)


        if (equipo != null) {
            if(equipo.listaJugadores.isEmpty()){
                println("El equipo ${equipo.nombreEquipo} no tiene jugadores registrados.")
                return
            }else{
                println("Eliga el id del jugador que desee eliminar")
                val idJugador = readLine() ?.toIntOrNull()?:0
                val jugadorEncontrado = equipo?.listaJugadores?.find { it.idJugador==idJugador }
                if(jugadorEncontrado!=null){
                    equipo.listaJugadores.remove(jugadorEncontrado)
                    println("Jugador eliminado correctamente del equipo ${equipo.nombreEquipo}.")
                    gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
                }else {
                    println("No se encontró un jugador con el ID $idJugador en el equipo ${equipo?.nombreEquipo}.")
                }
            }
        }else{
            println("No existe el equipo")
        }



    }
    fun actualizarJugadorEquipo(idEquipo: Int) {
        ListarEquipos()
        val equipo = equiposNuevos.find { it.idEquipo == idEquipo }
        listarEquiposJugadores(idEquipo)


        if (equipo != null) {
            if (equipo.listaJugadores.isEmpty()) {
                println("El equipo ${equipo.nombreEquipo} no tiene jugadores registrados.")
                return
            } else {
                println("Eliga el id del jugador que desee actualizar")
                val idJugador = readLine()?.toIntOrNull() ?: 0
                val jugadorEncontrado = equipo?.listaJugadores?.find { it.idJugador == idJugador }
                if (jugadorEncontrado != null) {

                    println("**********   Datos del Jugador: ${jugadorEncontrado.nombre} **************************")

                    println("Nueva edad del jugador: ")
                    jugadorEncontrado.edad = readLine()?.toIntOrNull() ?: jugadorEncontrado.edad
                    println("Nueva estatura del judador: ")
                    jugadorEncontrado.estatura = readLine()?.toDoubleOrNull() ?: jugadorEncontrado.estatura
                    println("Nuevo numero: ")
                    jugadorEncontrado.numero = readLine()?.toIntOrNull() ?: jugadorEncontrado.numero

                    gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
                    println("************Empleado actualizado************")
                } else {
                    println("No se encontró un jugador con el ID $idJugador en el equipo ${equipo?.nombreEquipo}.")
                }
            }
        } else {
            println("No existe el equipo")
        }
    }

    fun agregarJugadorEquipo(){
        println("Ingrese el ID del equipo al que desea agregar un jugador: ")
        val idSeleccionado = readLine()?.toIntOrNull()


        if (idSeleccionado != null) {
            val equipoEncontrado = buscarEquipoId(equiposNuevos, idSeleccionado)

            if (equipoEncontrado != null) {
                val nuevoJugador = crearJugador()
                equipoEncontrado.listaJugadores.add(nuevoJugador)
                gestionarArchivos.guardarEquiposArchivo(equiposNuevos)
                println("*****************************************************************************************")
                /*println("""
                            ID: ${equipoEncontrado.idEquipo}
                            Nombre: ${equipoEncontrado.nombreEquipo}
                            Año Fundacion: ${equipoEncontrado.AnoFundacion}
                            Tiene Juvenil: ${if(equipoEncontrado.tieneJuvenil) "Si" else "No"}
                            Categoria: ${equipoEncontrado.categoria}
                        """)*/

            } else {
                println("No se encontró ningun equipo con el ID: $idSeleccionado")
            }
        }

    }


    fun crearJugador(): Jugador {


        println("Ingrese el codigo del jugador: ")
        val idJugador = readLine()?.toIntOrNull() ?: 0
        println("Ingrese el nombre del jugador: ")
        val nombreJugador = readLine().orEmpty()
        println("Ingrese la edad del jugador: ")
        val edad = readLine()?.toIntOrNull() ?: 0
        println("Ingrese la estatura del jugador: ")
        val estatura = readLine()?.toDoubleOrNull() ?: 0.0
        println("Ingrese el numero del jugador: ")
        val numero = readLine()?.toIntOrNull() ?: 0
        val nuevoJugador = Jugador(idJugador, nombreJugador, edad, estatura, numero)
        return nuevoJugador

    }

    private fun generarId(equipos: List<Equipo>): Int {
        val ultimoId = equipos.maxByOrNull { it.idEquipo ?: 0 }?.idEquipo ?: 0
        return ultimoId + 1
    }


}


