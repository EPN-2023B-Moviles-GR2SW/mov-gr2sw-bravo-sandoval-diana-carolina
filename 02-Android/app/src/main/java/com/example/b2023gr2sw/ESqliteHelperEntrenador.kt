package com.example.b2023gr2sw

import android.content.ClipDescription
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(contexto: Context?,
    ): SQLiteOpenHelper(contexto, "moviles"// nombre base de datos
     , null,1)

{
    override fun onCreate(db: SQLiteDatabase?) {
       val scriptSQLCrearTablaEntrenador =
           """
               CREATE TABLE ENTRENADOR(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               descripcion VARCHAR(50)
               )
           """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {

    }

    fun crearEntrenador(
        nombre: String,
        descripcion: String
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoGuardar = basedatosEscritura.insert(
            "ENTRENADOR",//nombre tabla
             null, valoresAGuardar //valores para guardar
            )
        basedatosEscritura.close() //cerramos la base  de datos
        return if (resultadoGuardar.toInt()==-1) false else true //si es igual a -1 nose guarda
    }
//seusa la base de datos de escritura para la de eliminar y actualizar
    //la funcion eliminar, seusara un booleano para saber si se elimino o no
    //tambien se envia un parametro para saber que eliminar, ? se usa para
    //que no puedan hacer inyecciones sql
    fun eliminarEntrenadorFormulario(id: Int):Boolean{
        val conexionEscritura = writableDatabase
        //where ID = ?
        val parametroConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                //en la tabla entrenador vamos a buscar donde el id sea
                "Entrenador",//nombre tabla
                "id=?",//consulta wher
                //para que no nos hagan inyeccion 
            parametroConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt()==-1) false else true
    }

    //es un pequeño mix del eliminar y crear, tambien se usa una consulta de
    //busqueda,

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        id:Int,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues(        )
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion",descripcion)
        //where ID = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion  = conexionEscritura.
                update(


                    "ENTRENADOR", //nombre tabla
                    valoresAActualizar, //valores
                    "id=?",//consulta where
                    //para que no puedan hacernos inyeccion sql
                    parametrosConsultaActualizar
                )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt()==-1) false else true
    }

    //funcion para buscar un entrenador

    //Primero se usa la base de datos de lectura
    fun consultarEntrenadorPorID(id: Int): BEntrenador{ // se devuelve un entrenador
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM ENTRENADOR WHERE ID = ? 
        """.trimIndent() //se seleccionan todos los campos del entrenador
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta cruda (el select*from),
            parametrosConsultaLectura, // Parametros de conuslta que se necesita
        )




        //para procesar los datos
        // logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst() //nos movemos al primer registro
        val usuarioEncontrado = BEntrenador(0, "" , "")
        val arreglo = arrayListOf<BEntrenador>()
        if(existeUsuario){
            do{
                val id= resultadoConsultaLectura.getInt(0) // Indice 0, o el dtipo de dato que sea
                val nombre = resultadoConsultaLectura.getString(1) //se obtiene con el indice 1
                val descripcion = resultadoConsultaLectura.getString(2)// se obtiene la columna con el indice 2
                if(id != null){
                    // llenar el arreglo con un nuevo BEntrenador
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    //arreglo.add(usuarioEncontrado) para encontrar una lista de datos
                }
            } while (resultadoConsultaLectura.moveToNext()) //mientras exista un nuevo registro se ejecuta pero cuando ya no exista se termina
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }



}