package com.example.ibexamen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class agregarJugador : AppCompatActivity() {

    lateinit var adaptador: ArrayAdapter<Jugador>
    var equipoCrud = BaseDatosMemoria

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_jugador)

        //llamar metodo llenar datos
        cargarDatosJugador()

        val btnGuardarJugador = findViewById<Button>(R.id.btn_agregar_jugador)
        btnGuardarJugador.setOnClickListener {
            crearJugador()
            finish()

        }
        val btnCancelar = findViewById<Button>(R.id.btn_regresar_lv_jugador)
        btnCancelar.setOnClickListener {
            irActividad(VerJugadores::class.java)
        }
    }

    fun cargarDatosJugador() {
        val titulo = findViewById<TextView>(R.id.txt_editar_jugador)
        val nombreJugador = findViewById<EditText>(R.id.input_editar_nombre_jugador)
        val edad = findViewById<EditText>(R.id.input_editar_edad)
        val estatura = findViewById<EditText>(R.id.input_editar_estatura)
        val numero = findViewById<EditText>(R.id.input_editar_numero)
        val btncrear = findViewById<Button>(R.id.btn_agregar_jugador)

        val idJugador = intent.getIntExtra("idJugador", -1)

        if (idJugador == -1) {
            return
        }
        val jugadorEncontrado = BaseDatosMemoria.obtenerDatosJugador(idJugador)
        if (jugadorEncontrado != null) {
            titulo.text="Actualizar Jugador"
            nombreJugador.setText(jugadorEncontrado.nombreJugador.toString())
            edad.setText(jugadorEncontrado.edad.toString())
            estatura.setText(jugadorEncontrado.estatura.toString())
            numero.setText(jugadorEncontrado.numero.toString())
            btncrear.text="Actualizar Jugador"

        }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", intent.getIntExtra("idEquipo", -1))
        startActivity(intent)
    }

    fun crearJugador() {
        val nombreJugador = findViewById<EditText>(R.id.input_editar_nombre_jugador)
        val edad = findViewById<EditText>(R.id.input_editar_edad)
        val estatura = findViewById<EditText>(R.id.input_editar_estatura)
        val numero = findViewById<EditText>(R.id.input_editar_numero)


        val equipoId = intent.getIntExtra("idEquipo", -1)
        val nuevoJugador = Jugador(
            idJugador = 0,
            nombreJugador = nombreJugador.text.toString(),
            edad = edad.text.toString().toInt(),
            talla = estatura.text.toString().toDouble(),
            numero = numero.text.toString().toInt(),

            equipoId = equipoId,
        )
        val id = intent.getIntExtra("idJugador", -1)
        if (id == -1) {
            BaseDatosMemoria.agregarJugador(nuevoJugador)


        } else {
            nuevoJugador.idJugador = id
            BaseDatosMemoria.actualizarJugador(nuevoJugador)

        }

    }

}