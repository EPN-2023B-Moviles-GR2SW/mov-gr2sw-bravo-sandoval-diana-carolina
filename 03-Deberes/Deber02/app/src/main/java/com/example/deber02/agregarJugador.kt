package com.example.deber02

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02.database.BaseDeDatos

class agregarJugador : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_jugador)
        val btnCrearNJugador = findViewById<Button>(R.id.btn_guardarN_jugador)
        btnCrearNJugador.setOnClickListener {
            crearNuevoJugador()
            irActividad(VerJugadores::class.java)
        }

    }
    fun crearNuevoJugador(){
        val idJugador = findViewById<EditText>(R.id.idJugador)
        val nombreJugador = findViewById<EditText>(R.id.input__nombre_jugador)
        val edadJugador = findViewById<EditText>(R.id.input_edad)
        val estatura = findViewById<EditText>(R.id.input_estatura)
        val numero = findViewById<EditText>(R.id.input_numero)

        BaseDeDatos.tablaJugador!!.crearJugador(
            idJugador.text.toString().toInt(),
            nombreJugador.text.toString(),
            edadJugador.text.toString().toInt(),
            estatura.text.toString().toDouble(),
            numero.text.toString().toInt(),
            BaseDeDatos.equipoSeleccionado.idEquipo
        )
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
}