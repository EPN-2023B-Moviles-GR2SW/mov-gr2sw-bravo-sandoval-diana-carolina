package com.example.deber02

import android.content.DialogInterface
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
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.deber02.database.BaseDeDatos
import com.google.android.material.snackbar.Snackbar

class VerJugadores : AppCompatActivity() {
    var equipoSeleccionado = BaseDeDatos.equipoSeleccionado
    var jugadorEquipo = BaseDeDatos.tablaJugador?.getJugadoresEquipo(BaseDeDatos.equipoSeleccionado.idEquipo)
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_jugadores)
        cargarJugadores()
        val botonRegresarEquipo = findViewById<Button>(R.id.btn_cancelar_jugador)
        botonRegresarEquipo.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonAgregarLv = findViewById<Button>(R.id.btn_ir_crear_jugador)
        botonAgregarLv.setOnClickListener {
            agregarNuevoJugador()
        }
        val listView = findViewById<ListView>(R.id.ls_ver_jugadores)
        registerForContextMenu(listView)

    }

    private fun cargarJugadores(){
        val listView = findViewById<ListView>(
            R.id.ls_ver_jugadores
        )
        jugadorEquipo = BaseDeDatos.tablaJugador?.getJugadoresEquipo(
            BaseDeDatos.equipoSeleccionado.idEquipo
        )
        if(jugadorEquipo !=null){
            val adapter = ArrayAdapter(
                this,android.R.layout.simple_list_item_1,
                jugadorEquipo!!
            )
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            registerForContextMenu(listView)

        }
    }

    fun agregarNuevoJugador(){
        irActividad(agregarJugador::class.java)
        cargarJugadores()
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_jugador, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        BaseDeDatos.jugadorSeleccionado = equipoSeleccionado.jugadores!!.get(posicion)

    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_actualizar_jugador -> {
                irActividad(editarJugador::class.java)
                return true
            }
            R.id.mi_eliminar_jugador -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.ls_ver_jugadores),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarJugador()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarJugador () {
        BaseDeDatos.tablaJugador!!.eliminarJugador(BaseDeDatos.jugadorSeleccionado.idJugador)
        cargarJugadores()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }




}