package com.example.ibexamen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class VerJugadores : AppCompatActivity() {
    var arregloViewJugadores = BaseDatosMemoria.arregloBJugador
    var idItemSeleccionado = 0
    var idEquipo = -1
    lateinit var adaptador: ArrayAdapter<Jugador>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_jugadores)
        actualizarListJugador()

        val btnagregarJugador =findViewById<Button>(R.id.btn_ir_crear_jugador)
        btnagregarJugador.setOnClickListener {
            irActividad(agregarJugador::class.java)
        }
        val btncancelar = findViewById<Button>(R.id.btn_cancelar_jugador)
        btncancelar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        idEquipo=intent.getIntExtra("idEquipo",-1)
        arregloViewJugadores=BaseDatosMemoria.obtenerEquipoJugadorId(idEquipo)
        actualizarListJugador()


    }





    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_actualizar_jugador -> {
                val idJugador = idItemSeleccionado
                val intent = Intent(this, agregarJugador::class.java)
                intent.putExtra("idJugador", idJugador)
                startActivity(intent)

                return true
            }

            R.id.mi_eliminar_jugador-> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jugador, menu)
        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = arregloViewJugadores[id].idJugador
    }


    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton("Si")
        { // Callback
                dialog, _ ->
            if (idItemSeleccionado.let { BaseDatosMemoria.eliminarJugador(it) }) {
                arregloViewJugadores= BaseDatosMemoria.obtenerEquipoJugadorId(idEquipo)
                actualizarListJugador()
                mostrarSnackbar("Jugador Eliminado exitosamente")
            }


            }
        builder.setNegativeButton("No", null)
        val dialogo = builder.create()
        dialogo.show()
        }




    fun actualizarListJugador() {
        val listView = findViewById<ListView>(R.id.ls_ver_jugadores)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDatosMemoria.arregloBJugador
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo", idEquipo)
        actualizarListJugador()
        startActivity(intent)

    }
    override fun onRestart() {
        super.onRestart()
        actualizarListJugador()
    }


    override fun onResume() {
        super.onResume()
        actualizarListJugador()
    }
    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}