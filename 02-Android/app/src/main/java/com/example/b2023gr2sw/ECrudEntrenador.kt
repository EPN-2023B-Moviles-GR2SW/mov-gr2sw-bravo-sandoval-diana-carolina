package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.compose.material3.Snackbar

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)
        // Buscar Entrenador
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            // Consultar componentes visuales
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(
                R.id.input_descripcion
            )
            // Consultar SQLite
            val entrenador = EBaseDeDatos.tablaEntrenador!!
                .consultarEntrenadorPorID(
                    id.text.toString().toInt()
                )
            // Setear el texto en componentes visuales
            id.setText(entrenador.id.toString())
            nombre.setText(entrenador.nombre)
            descripcion.setText(entrenador.descripcion)
        }

        val botonActualizarBDD =findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val descripcion = findViewById<EditText>(R.id.input_descripcion)
            val respuesta = EBaseDeDatos.tablaEntrenador!!.actualizarEntrenadorFormulario(nombre.text.toString(),
                descripcion.text.toString(),id.text.toString().toInt())
            if(respuesta) mostrarSnackbar("Usu.Actualizado")
                    }

        val botonEliminarBDD = findViewById<
                Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val respuesta = EBaseDeDatos.tablaEntrenador!!.eliminarEntrenadorFormulario(id.text.toString().toInt())
            if(respuesta
                )mostrarSnackbar("Usu. Eliminado")
        }

        fun mostrarSnackbar(texto: String){
           Snackbar.make(findViewById(R.id.cl_sqlite),
               texto,
               Snackbar.LENGTH_LONG).show
        }
    }

}