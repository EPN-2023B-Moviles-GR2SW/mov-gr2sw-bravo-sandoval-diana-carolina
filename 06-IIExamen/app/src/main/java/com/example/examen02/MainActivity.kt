package com.example.examen02

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
import androidx.appcompat.app.AlertDialog
import com.example.examen02.database.Firestore
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
   var idEquipoSeleccionado = 0
    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cargarEquipos()
        val botonAnadirListaEquipos = findViewById<Button>(R.id.btn_ir_Agregarequipo)
        botonAnadirListaEquipos.setOnClickListener {
            anadirEquipo()
        }
        /*val listView = findViewById<ListView>(
            R.id.lv_listarEquipo
        )
        registerForContextMenu(listView)*/
    }
    private fun cargarEquipos(){
        val listView = findViewById<ListView>(
            R.id.lv_listarEquipo
        )
        Firestore.consultarEquipos {
            if(it!=null){
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    it!!
                )
                listView.adapter=adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(listView)

            }
        }

    }

    fun anadirEquipo(){
        irActividad(agregarEquipo::class.java)
        cargarEquipos()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_equipo, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        Firestore.consultarEquipos { idEquipoSeleccionado= it[posicion].idEquipo.toInt() }
        }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_EditarEquipo -> {
                irActividad(editarEquipo::class.java)
                return true
            }
            R.id.mi_EliminarEquipo -> {
                abrirDialogo()
                return true
            }
            R.id.mi_VerJugadores -> {
                irActividad(VerJugadores::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lv_listarEquipo),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarEquipo()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }


    fun eliminarEquipo () {
       mostrarSnackbar(idEquipoSeleccionado.toString())
        Firestore.eliminarEquipo(idEquipoSeleccionado.toLong())
        cargarEquipos()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idEquipo",idEquipoSeleccionado)
        startActivity(intent)
    }
}