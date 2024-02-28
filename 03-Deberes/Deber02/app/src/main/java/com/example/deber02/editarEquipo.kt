package com.example.deber02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02.database.BaseDeDatos

class editarEquipo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_equipo)

        val idEquipo = findViewById<EditText>(R.id.input_editar_idEquipo)
        val nombreEquipo = findViewById<EditText>(R.id.input_editar_NombreEquipo)
        val fechaFundacion = findViewById<EditText>(R.id.input_Editarfecha_fundacion)
        val categoria = findViewById<EditText>(R.id.input_Editarcategoria)

        idEquipo.setText(BaseDeDatos.equipoSeleccionado.idEquipo.toString())
        nombreEquipo.setText(BaseDeDatos.equipoSeleccionado.nombre)
        fechaFundacion.setText(BaseDeDatos.equipoSeleccionado.fecha_fundacion.toString())
        categoria.setText(BaseDeDatos.equipoSeleccionado.categoria)

        val btnEditarEquipo = findViewById<Button>(R.id.btn_GuardarCambiosEquipo)
        btnEditarEquipo.setOnClickListener {
            editarEquipo()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_Editarequipo)
        btnCancelar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

    }

    fun editarEquipo(){
        val idEquipo = findViewById<EditText>(R.id.input_editar_idEquipo)
        val nombreEquipo = findViewById<EditText>(R.id.input_editar_NombreEquipo)
        val fechaFundacion = findViewById<EditText>(R.id.input_Editarfecha_fundacion)
        val categoria = findViewById<EditText>(R.id.input_Editarcategoria)
        BaseDeDatos.tablaEquipo!!.actualizarEquipo(
            idEquipo.text.toString().toInt(),
            nombreEquipo.text.toString(),
            fechaFundacion.text.toString().toInt(),
            categoria.text.toString()
        )
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}