package com.example.ibexamen

import android.content.Context
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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0
    var idBorrar = 0
    lateinit var adaptador: ArrayAdapter<Equipo>
    val equipo = BaseDatosMemoria.arregloBEquipo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adaptador
        val listViewEquipo = findViewById<ListView>(R.id.lv_listarEquipo)

        adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, //
            BaseDatosMemoria.arregloBEquipo
        )
        listViewEquipo.adapter = adaptador
        adaptador.notifyDataSetChanged()



        val botonCrearEquip = findViewById<Button>(R.id.btn_ir_Agregarequipo)
        botonCrearEquip.setOnClickListener {
            irActividad(agregarEquipo::class.java)
        }
        registerForContextMenu(listViewEquipo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_VerJugadores -> {

                try {
                    val idEquipo = idItemSeleccionado
                    irActividad(VerJugadores::class.java, idEquipo)
                } catch (e: Throwable) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }

                return true
            }

            R.id.mi_EditarEquipo -> {
                val idEquipo = idItemSeleccionado
                irActividad(agregarEquipo::class.java, idEquipo)
                return true
            }

            R.id.mi_EliminarEquipo-> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton("Si")
        { // Callback
                dialog, _ ->
            if (idItemSeleccionado.let { BaseDatosMemoria.eliminarEquipo(it) }) {
                actualizarListEquipo()
                mostrarSnackbar("Equipo eliminado exitosamente")

            }
        }
        builder.setNegativeButton("No", null)
        val dialogo = builder.create()
        dialogo.show()
    }



    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_equipo, menu)
        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val equipId = equipo[info.position].idEquipo

        if (equipId != null) {
            idItemSeleccionado = equipId
        }

        val idBorrarEquipo = info.id.toInt()
        idBorrar = idBorrarEquipo
    }



    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)

    }

    fun irActividad(clase: Class<*>, id: Int?) {
        val intent = Intent(this, clase)

        intent.putExtra("idEquipo", id)
        startActivity(intent)

    }


    private fun actualizarListEquipo() {
        val listView = findViewById<ListView>(R.id.lv_listarEquipo)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BaseDatosMemoria.arregloBEquipo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onRestart() {
        super.onRestart()
        actualizarListEquipo()
    }

    override fun onResume() {
        super.onResume()
        actualizarListEquipo()
    }
    private fun mostrarSnackbar(mensaje: String) {
        val rootView: View = findViewById(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}