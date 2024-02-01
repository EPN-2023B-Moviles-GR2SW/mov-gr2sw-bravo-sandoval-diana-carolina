package com.example.deber02.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.deber02.modelo.Equipo
import com.example.deber02.modelo.Jugador

class SqliteHelperJugador(contexto: Context,
    ):SQLiteOpenHelper(
        contexto,
        "jugadores", null,
        1
    ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaJugador =
            """
                CREATE TABLE JUGADOR(
                ID_JUGADOR INTEGER PRIMARY KEY,
                NOMBRE VARCHAR(100),
                EDAD INTEGER,
                ESTATURA DOUBLE,
                NUMERO INTEGER,
                IDEQUIPO INTEGER,
                 FOREIGN KEY(IDEQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaJugador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearJugador(
        idJugador: Int,
        nombre: String,
        edad: Int,
        estatura:Double,
        numero: Int,
        idEquipo: Int,
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("ID_JUGADOR",idJugador)
        valoresGuardar.put("NOMBRE",nombre)
        valoresGuardar.put("EDAD",edad)
        valoresGuardar.put("ESTATURA",estatura)
        valoresGuardar.put("NUMERO",numero)
        valoresGuardar.put("IDEQUIPO",idEquipo)
        val resultadoGuardar = basedatosEscritura.insert(
            "JUGADOR",
            null,
            valoresGuardar
        )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt()==-1) false else true
    }

    fun eliminarJugador(idJugador: Int):Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf( idJugador.toString() )
        val resultadoEliminar = conexionEscritura
            .delete(
                "JUGADOR",
                "ID_JUGADOR=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminar.toInt() ==-1)false
        else true
    }

    fun actualizarJugador(
        idJugador: Int,
        nombre: String,
        edad: Int,
        estatura:Double,
        numero: Int,
        idEquipo: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("ID_EQUIPO",idJugador)
        valoresActualizar.put("NOMBRE", nombre)
        valoresActualizar.put("EDAD",edad)
        valoresActualizar.put("ESTATURA",estatura)
        valoresActualizar.put("NUMERO",numero)
        valoresActualizar.put("IDEQUIPO",idEquipo)
        val parametrosConsultaActualizar = arrayOf(idJugador.toString())
        val resultadoActualizar = conexionEscritura.update(
            "JUGADOR",
            valoresActualizar,
            "ID_JUGADOR=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if(resultadoActualizar.toInt()==-1) false else true
    }

    fun consultarJugadorbyId(idJugador: Int): Jugador {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM JUGADOR WHERE ID_JUGADOR = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idJugador.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )
        val existeJugador = resultadoConsultaLectura.moveToFirst()
        var jugadorEncontrado = Jugador(0,"",0,0.0,0)
        if(existeJugador){
            do{
                val idJugador = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val edad = resultadoConsultaLectura.getInt(2)
                val estatura = resultadoConsultaLectura.getDouble(3)
                val numero = resultadoConsultaLectura.getInt(4)
                if(idJugador != null){
                    val jugador = Jugador(
                        idJugador,nombre,edad,estatura,numero
                    )
                    jugadorEncontrado = jugador
                }

            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return jugadorEncontrado
    }

    fun getAll(): MutableList<Jugador>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM JUGADOR
        """.trimIndent()
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura,null
        )
        val exists = result.moveToFirst()
        val jugadores = arrayListOf<Jugador>()

        if(exists){
            do{
                val idJugador = result.getInt(0)
                val nombre = result.getString(1)
                val edad= result.getInt(2)
                val estatura = result.getDouble(3)
                val numero = result.getInt(4)

                if(idJugador!= null){
                    val jugador = Jugador(
                        idJugador,
                        nombre,
                        edad,
                        estatura,
                        numero
                    )
                    jugadores.add(jugador)
                }
            }while (
                result.moveToNext()
            )
        }
        result.close()
        baseDatosLectura.close()
        return jugadores
    }

    fun getJugadoresEquipo(idEquipo: Int): MutableList<Jugador>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM JUGADOR 
            WHERE IDEQUIPO =?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idEquipo.toString())
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )
        val exists = result.moveToFirst()
        val jugadores = arrayListOf<Jugador>()
        if(exists){
            do{
                val idJugador = result.getInt(0)
                val nombre = result.getString(1)
                val edad= result.getInt(2)
                val estatura = result.getDouble(3)
                val numero = result.getInt(4)
                if(idJugador!= null){
                    val jugador = Jugador(
                        idJugador,
                        nombre,
                        edad,
                        estatura,
                        numero
                    )
                    jugadores.add(jugador)
                }

            }while (result.moveToNext())
        }
        return jugadores
    }


}