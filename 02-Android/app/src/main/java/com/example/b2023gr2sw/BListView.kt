package com.example.b2023gr2sw
import android.content.DialogInterface
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

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener {
                anadirEntrenador(adaptador)
            }

        registerForContextMenu(listView)
    }
    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(
                1,
                "Adrian",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                mostrarSnackbar("${posicionItemSeleccionado}")
                return true
            }
            R.id.mi_eliminar ->{
                mostrarSnackbar("${posicionItemSeleccionado}")
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                mostrarSnackbar("Acepto ${which}")
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, // Lunes seleccionado
            false, // Martes NO seleccionado
            false // Miercoles NO seleccionado
        )
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            { dialog,
              which,
              isChecked ->
                mostrarSnackbar("Item: ${which}")
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }





    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lv_list_view),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

}

// import android.content.DialogInterface
// import androidx.appcompat.app.AppCompatActivity
// import android.os.Bundle
// import android.view.ContextMenu
// import android.view.MenuItem
// import android.view.View
// import android.widget.AdapterView
// import android.widget.ArrayAdapter
// import android.widget.Button
// import android.widget.ListView
// import androidx.appcompat.app.AlertDialog
// import com.google.android.material.snackbar.Snackbar
//
// class BListView : AppCompatActivity() {
// val arreglo = BBaseDatosMemoria.arregloBEntrenador
// var posicionItemSeleccionado = 0
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// setContentView(R.layout.activity_blist_view)
// val listView = findViewById<ListView>(R.id.lv_list_view)//capturamos el list view que agregamos
// val adaptador = ArrayAdapter(//creamos este adaptador uno que existe
// this,//el contexto en el que usamos
// android.R.layout.simple_list_item_1,//como se va a ver
// arreglo //arreglo
// )
// listView.adapter = adaptador
// adaptador.notifyDataSetChanged()
// //implementacion de que cuando se de clic, solo lo vamos a dejar escuchando
// val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
//
//
// botonAnadirListView.setOnClickListener {
// anadirEntrenador(adaptador)
// }
// //se debe registrar el list view para el menu registrar
// registerForContextMenu(listView)
// }
// fun anadirEntrenador(
// adaptador: ArrayAdapter<BEntrenador>
// ){
// arreglo.add(BEntrenador(
// 1,"Adrian", "Descripcion"
// ))
// adaptador.notifyDataSetChanged()
// }
//
// override fun onCreateContextMenu(
// menu: ContextMenu?,
// v: View?,
// menuInfo: ContextMenu.ContextMenuInfo?
// ) {
// super.onCreateContextMenu(menu, v, menuInfo)
// // Llenamos las opciones del menu
// val inflater = menuInflater
// inflater.inflate(R.menu.menu, menu)
// // Obtener el id del ArrayListSeleccionado
// val info = menuInfo as AdapterView.AdapterContextMenuInfo
// val posicion = info.position //guardamos la item el posicion seleccionado como variable globa
// //sabemos la posicion que esta escogiendo
// posicionItemSeleccionado = posicion
// }
// //reescribimos, nos llega el item del menu que selecciono
// //dependiendo de l identificador se hace algo
// override fun onContextItemSelected(item: MenuItem): Boolean {
// return when (item.itemId){ //se ve el identificador de identificador que llega
// //si se esque es se hace lo que se le pide es como un switch
// R.id.mi_editar ->{
// //cuando se abra el menu contextual
// mostrarSnackbar("${posicionItemSeleccionado}")
// return true
// }
// R.id.mi_eliminar ->{
// mostrarSnackbar("${posicionItemSeleccionado}")
// abrirDialogo()
// return true //se devuelve siempre un booleano
// }
// else -> super.onContextItemSelected(item)
// }
// }
//
// fun abrirDialogo(){
// val builder = AlertDialog.Builder(this)
// builder.setTitle("Desea eliminar")
// builder.setPositiveButton(
// "Aceptar",
// DialogInterface.OnClickListener { dialog, which ->
// mostrarSnackbar("Acepto ${which}")
// }
// )
// builder.setNegativeButton(
// "Cancelar",
// null
// )
//
// val opciones = resources.getStringArray(
// R.array.string_array_opciones_dialogo
// )
// val seleccionPrevia = booleanArrayOf(
// true, // Lunes seleccionado
// false, // Martes NO seleccionado
// false // Miercoles NO seleccionado
// )
// builder.setMultiChoiceItems(
// opciones,
// seleccionPrevia,
// { dialog,
// which,
// isChecked ->
// mostrarSnackbar("Item: ${which}")
// }
// )
// val dialogo = builder.create()
// dialogo.show()
// }
//
//
// fun mostrarSnackbar(texto:String){
// val snack = Snackbar.make(findViewById(R.id.lv_list_view),
// texto, Snackbar.LENGTH_LONG)// longitud larga normal
// snack.show()
// }
// }