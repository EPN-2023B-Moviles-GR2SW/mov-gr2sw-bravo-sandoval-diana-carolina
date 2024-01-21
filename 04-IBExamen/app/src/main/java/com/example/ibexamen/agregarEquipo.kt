package com.example.ibexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class agregarEquipo : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_agregar_equipo)
            cargarDatos()
            val btnCrearEquipo = findViewById<Button>(R.id.btn_anadir_Equipo)
            btnCrearEquipo.setOnClickListener {




                    crearEquipo()






            }
            val btnCancelar = findViewById<Button>(R.id.btn_cancelar_equipo)
            btnCancelar.setOnClickListener {
                irActividad(MainActivity::class.java)
            }
        }
    fun cargarDatos(){
        val titulo = findViewById<TextView>(R.id.txtTituloCrearEquipo)
        val nombreEquipo = findViewById<EditText>(R.id.input_NombreEquipo)
        val anioFundacion = findViewById<EditText>(R.id.input_fecha_fundacion)
        val categoria = findViewById<EditText>(R.id.input_categoria)
        val idEquipo = intent.getIntExtra("idEquipo",-1)
        val btnActualizar = findViewById<Button>(R.id.btn_anadir_Equipo)
        if(idEquipo == -1){
            return
        }
        val equipoEncontrado = BaseDatosMemoria.obtenerDatosEquipo(idEquipo)
        if(equipoEncontrado!=null){
            titulo.text="Actualizar Equipo"
            nombreEquipo.setText(equipoEncontrado.nombreEquipo.toString())
            anioFundacion.setText(equipoEncontrado.fechaFundacion.toString())
            categoria.setText(equipoEncontrado.categoria.toString())
            btnActualizar.text ="Actualizar"

        }

    }

    fun crearEquipo(){
        val nombreEquipo = findViewById<EditText>(R.id.input_NombreEquipo)
        val  anoFundacion = findViewById<EditText>(R.id.input_fecha_fundacion)
        val categoria = findViewById<EditText>(R.id.input_categoria)

        // Verificar si todos los campos están completos
        if (nombreEquipo.text.isBlank() || anoFundacion.text.isBlank() || categoria.text.isBlank()) {
            mostrarSnackbar("Completa todos los campos antes de agregar el equipo")
            return
        }
        val nuevoEquipo = Equipo(idEquipo = 0,nombreEquipo.text.toString(),anoFundacion.text.toString().toIntOrNull() ?:0,categoria.text.toString())
        val id = intent.getIntExtra("idEquipo",-1)
        if(id == -1){
            BaseDatosMemoria.agregarEquipo(nuevoEquipo)
            mostrarSnackbar("Equipo agregado exitosamente")
        }else{
            nuevoEquipo.idEquipo=id
            BaseDatosMemoria.actualizarEquipo(nuevoEquipo)
            finish()
        }

    }

    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }


}