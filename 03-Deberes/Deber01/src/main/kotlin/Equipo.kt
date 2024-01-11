
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Equipo(
    var idEquipo: Int?,
    val nombreEquipo: String,
    val fechaFundacion: LocalDate,
    val tieneJuvenil: Boolean,
    val categoria: String,
    val directorTecnico: String,
    val jugadores: MutableList<Jugador> = mutableListOf()

) {
    constructor() : this(null,"",LocalDate.now(),false,"","")
}

