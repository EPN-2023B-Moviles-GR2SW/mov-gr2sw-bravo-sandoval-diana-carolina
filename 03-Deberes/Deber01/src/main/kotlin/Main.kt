

fun main() {
    var exit = false

    val archivoEquipos = EquipoManager("EquiposGuardados1.txt")
    val equipos = archivoEquipos.cargarEquiposArchivo().toMutableList()

    val equipoCRUD = CRUD(equipos,archivoEquipos)
    val menuEquipo = EquipoMenu(equipoCRUD)
    val menuJugador =menuJugador(equipoCRUD)

    while (!exit) {
        println("--------------------------------------------------")
        println("Bienvenido.")
        print("--------------------------------------------------")
        println("\nMenú:")

        println("1. Menú de Equipos")
        println("2. Menú de Jugadores")
        println("3. Salir")

        print("\nSeleccione una opción: ")
        when (readLine()?.toIntOrNull() ?: 3) {
            1 -> {
                menuEquipo.menuEquipo()
            }
            2 -> {
                menuJugador.menuJugador()
            }


            3 -> {
                println("\n Adios")
                exit = true
            }

            else -> println("\nOpción inválida. Inténtelo de nuevo.")
        }
    }
}
    fun leerEquipoJugador(){


    }

class menuJugador(private val equipoCRUD: CRUD){

    fun menuJugador(){
        var exit=false
        while (!exit) {
            println("\nIngrese la opcion de la operacion que desea realizar :")
            println("1. Crear Jugador ")
            println("2. Actualizar Jugador ")
            println("3. Listar Jugadores segun su Equipo")
            println("4.Eliminar Jugador")
            println("5.Menu principal")

            print("Seleccione una opción: ")
            when (readLine()?.toIntOrNull() ?: 5) {
                1 -> {
                    equipoCRUD.ListarEquipos()
                    equipoCRUD.agregarJugadorEquipo()
                }

                2 -> {
                    equipoCRUD.ListarEquipos()
                    println("Eliga el equipo que desea actualizar un jugador:")
                    val idEquipo = readLine()?.toIntOrNull()?:0
                    equipoCRUD.listarEquiposJugadores(idEquipo)
                    equipoCRUD.actualizarJugadorEquipo(idEquipo)

                }

                3 -> {
                    println("Eliga el Equipo que desea ver los jugadores")
                    equipoCRUD.ListarEquipos()
                    if(equipoCRUD!=null) {
                        print("Ingrese el codigo del equipo: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        equipoCRUD.listarEquiposJugadores(id)
                    }else{
                        return
                    }
                }

                4 -> {

                    equipoCRUD.ListarEquipos()
                    println("Eliga el equipo que desea eliminar un jugador")
                    val idEquipo = readLine()?.toIntOrNull()?:0
                    equipoCRUD.listarEquiposJugadores(idEquipo)
                    equipoCRUD.eliminarJugador(idEquipo)
                }

                5 -> {

                    exit = true
                }


                else -> {
                    println("\nOpción inválida. Inténtelo de nuevo.")
                }
            }
        }
    }
}


class EquipoMenu(private val equipoCRUD: CRUD){
    fun menuEquipo( ){


        var exit=false

        while (!exit) {
            println("\nIngrese la opcion de la operacion que desea realizar :")
            println("1. Crear Equipo ")
            println("2. Mostrar Equipos con la lista de sus jugadores inscritos")
            println("3. Actualizar Equipo ")
            println("4. Consultar Equipo por ID")
            println("5. Listar todos los equipos")
            println("6.Eliminar Equipo")
            println("7.Menu principal")

            print("Seleccione una opción: ")
            when (readLine()?.toIntOrNull() ?: 7) {
                1 -> {

                    equipoCRUD.crearEquipo()

                }

                2 -> {

                    println("Eliga el Equipo que desea ver los jugadores")
                    equipoCRUD.ListarEquipos()
                    if(equipoCRUD!=null) {
                        print("Ingrese el codigo del equipo: ")
                        val id = readLine()?.toIntOrNull() ?: 0
                        equipoCRUD.listarEquiposJugadores(id)
                    }else{
                        return
                    }
                }

                3 -> {
                    println("Eliga el Equipo para actualizar")
                    equipoCRUD.ListarEquipos()
                    print("Ingrese el codigo del equipo: ")
                    val idSeleccionado = readLine() ?.toIntOrNull() ?:0
                    equipoCRUD.ActualizarEquipo(idSeleccionado)

                }

                4 -> {
                    equipoCRUD.equipoSelecionar()
                }

                5 -> {
                    equipoCRUD.ListarEquipos()
                }

                6 -> {
                    println("Eliga el Equipo para eliminar")
                    equipoCRUD.ListarEquipos()
                    print("Ingrese el codigo del equipo: ")
                    val idSeleccionado = readLine() ?.toIntOrNull() ?:0
                    equipoCRUD.eliminarEquipo(idSeleccionado)
                }

                7 ->{
                    exit = true
                }


                else -> {
                    println("\nOpción inválida. Inténtelo de nuevo.")
                }
            }
        }
    }
}







