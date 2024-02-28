package com.example.deber02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.deber02.database.BaseDeDatos

class editarJugador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_jugador)
        val idJugador = findViewById<EditText>(R.id.input_editar_idJugador)
        val nombreJugador = findViewById<EditText>(R.id.input_editar_nombre_jugador)
        val edad = findViewById<EditText>(R.id.input_editar_edad)
        val estatura = findViewById<EditText>(R.id.input_editar_estatura)
        val numero = findViewById<EditText>(R.id.input_editar_numero)

        idJugador.setText(BaseDeDatos.jugadorSeleccionado.idJugador)
        nombreJugador.setText(BaseDeDatos.jugadorSeleccionado.nombreJugador)
        edad.setText(BaseDeDatos.jugadorSeleccionado.edad.toString())
        estatura.setText(BaseDeDatos.jugadorSeleccionado.estatura.toString())
        numero.setText(BaseDeDatos.jugadorSeleccionado.numero.toString())

        val btnEditarJugador = findViewById<Button>(R.id.btn_guardarN_jugador)
        btnEditarJugador.setOnClickListener {
            editarJugadorNuevo()
            irActividad(VerJugadores::class.java)
        }

        val btnCancelar =findViewById<Button>(R.id.btn_regresar_lv_jugadores)
        btnCancelar.setOnClickListener {
            irActividad(VerJugadores::class.java)
        }

    }
    fun editarJugadorNuevo(){
        val idJugador = findViewById<EditText>(R.id.input_editar_idJugador)
        val nombreJugador = findViewById<EditText>(R.id.input_editar_nombre_jugador)
        val edad = findViewById<EditText>(R.id.input_editar_edad)
        val estatura = findViewById<EditText>(R.id.input_editar_estatura)
        val numero = findViewById<EditText>(R.id.input_editar_numero)

        BaseDeDatos.tablaJugador!!.actualizarJugador(
            idJugador.text.toString().toInt(),
            nombreJugador.text.toString(),
            edad.text.toString().toInt(),
            estatura.text.toString().toDouble(),
            numero.text.toString().toInt(),
            BaseDeDatos.equipoSeleccionado.idEquipo
        )
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}