package com.example.deber02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02.database.BaseDeDatos

class agregarEquipo : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_equipo)
    val btnCrearNuevoEquipo = findViewById<Button>(R.id.btn_GuardarCambiosEquipo)
        btnCrearNuevoEquipo.setOnClickListener {
            crearEquipo()
            irActividad(MainActivity::class.java)
        }

        val btnCancelarEquipo = findViewById<Button>(R.id.btn_cancelar_Editarequipo)
        btnCancelarEquipo.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

   }
    fun crearEquipo(){
        val idEquipo = findViewById<EditText>(R.id.input_editar_idEquipo)
        val nombreEquipo=findViewById<EditText>(R.id.input_editar_NombreEquipo)
        val fechaFundacion = findViewById<EditText>(R.id.input_Editarfecha_fundacion).text
        val categoria = findViewById<EditText>(R.id.input_Editarcategoria)

        BaseDeDatos.tablaEquipo!!.crearEquipo(idEquipo.text.toString().toInt(),
            nombreEquipo.text.toString(), fechaFundacion.toString().toString().toInt(),
            categoria.text.toString()
        )
    }

    fun irActividad(
        clase: Class<*>)
    {
            val intent = Intent(this, clase)
        startActivity(intent)
        }

}