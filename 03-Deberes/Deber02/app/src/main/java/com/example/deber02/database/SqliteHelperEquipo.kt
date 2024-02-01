package com.example.deber02.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.contentcapture.ContentCaptureSession
import androidx.core.content.contentValuesOf
import com.example.deber02.modelo.Equipo

class SqliteHelperEquipo(contexto:
Context,): SQLiteOpenHelper(
    contexto,"equipos",
    null,1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEquipo =
            """
                CREATE TABLE EQUIPO(
                ID_EQUIPO INTEGER PRIMARY KEY,
                NOMBRE VARCHAR(100),
                FECHA_FUNDACION INTEGER,
                CATEGORIA VARCHAR(50)              
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEquipo)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {

    }

    fun crearEquipo(
        idEquipo: Int,
        nombre: String,
        fecha_fundacion: Int,
        categoria: String,
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresGuardar = ContentValues()
        valoresGuardar.put("ID_EQUIPO",idEquipo)
        valoresGuardar.put("NOMBRE",nombre)
        valoresGuardar.put("FECHA_FUNDACION",fecha_fundacion)
        valoresGuardar.put("CATEGORIA",categoria)
        val resultadoGuardar = basedatosEscritura.insert(
            "EQUIPO",
            null,
            valoresGuardar
        )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt()==-1) false else true
    }

    fun eliminarEquipo(idEquipo: Int):Boolean{
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf( idEquipo.toString() )
        val resultadoEliminar = conexionEscritura
            .delete(
            "EQUIPO",
            "ID_EQUIPO=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return if (resultadoEliminar.toInt() ==-1)false
        else true
    }

    fun actualizarEquipo(
        idEquipo: Int,
        nombre: String,
        fecha_fundacion: Int,
        categoria: String,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("ID_EQUIPO",idEquipo)
        valoresActualizar.put("NOMBRE", nombre)
        valoresActualizar.put("FECHA_FUNDACION",fecha_fundacion)
        valoresActualizar.put("CATEGORIA",categoria)
        val parametrosConsultaActualizar = arrayOf(idEquipo.toString())
        val resultadoActualizar = conexionEscritura.update(
            "EQUIPO",
                valoresActualizar,
            "ID_EQUIPO=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return if(resultadoActualizar.toInt()==-1) false else true
    }

    fun consultarEquipobyId(idEquipo: Int): Equipo {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM EQUIPO WHERE ID_EQUIPO = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idEquipo.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura,
        )
        val existeEquipo = resultadoConsultaLectura.moveToFirst()
        var equipoEncontrado = Equipo(0,"",0,"")
        if(existeEquipo){
            do{
                val idEquipo = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val fecha_fundacion = resultadoConsultaLectura.getInt(2)
                val categoria = resultadoConsultaLectura.getString(3)
                if(idEquipo != null){
                    val equipo = Equipo(
                        idEquipo,nombre,fecha_fundacion,categoria
                    )
                    equipoEncontrado = equipo
                }
                
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return equipoEncontrado
    }
    
    fun getAll(): MutableList<Equipo>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM EQUIPO
        """.trimIndent()
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura,null
        )
        val exists = result.moveToFirst()
        val equipos = arrayListOf<Equipo>()

        if(exists){
            do{
                val idEquipo = result.getInt(0)
                val nombre = result.getString(1)
                val fecha_fundacion= result.getInt(2)
                val categoria = result.getString(3)

                if(idEquipo!= null){
                    val equipo = Equipo(
                        idEquipo,
                        nombre,
                        fecha_fundacion,
                        categoria
                    )
                    equipos.add(equipo)
                }
            }while (
                result.moveToNext()
            )
        }
        result.close()
        baseDatosLectura.close()
        return equipos
    }

}

