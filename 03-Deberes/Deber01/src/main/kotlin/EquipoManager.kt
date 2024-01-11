import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class EquipoManager(val archivoEquipos: File){
    var equipos: MutableList<Equipo>
    init {
        equipos = cargarEquipos()
    }

    fun cargarEquipos(): MutableList<Equipo>{
        if(archivoEquipos.exists()){
            val jsonString = archivoEquipos.readText()
            return Json.decodeFromString<MutableList<Equipo>>(jsonString)

        }
        return mutableListOf()
    }

    fun guardarEquipos(){
        val jsonString = Json.encodeToString(equipos)
        archivoEquipos.writeText(jsonString)
    }

    fun crearEquipo(equipo: Equipo){
        equipo.idEquipo = equipos.size + 1
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
    }

    }


