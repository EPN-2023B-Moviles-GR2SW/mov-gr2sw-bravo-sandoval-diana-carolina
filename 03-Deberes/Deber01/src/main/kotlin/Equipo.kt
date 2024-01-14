


class Equipo(
    var idEquipo: Int?,
    var nombreEquipo: String,
    var AnoFundacion: Int,
    var tieneJuvenil: Boolean,
    var categoria: String,
    val listaJugadores: MutableList<Jugador> = mutableListOf()

)

