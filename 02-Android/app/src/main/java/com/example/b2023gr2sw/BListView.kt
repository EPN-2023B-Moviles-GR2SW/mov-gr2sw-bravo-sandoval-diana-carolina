package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)//capturamos el list view que agregamos
        val adaptador = ArrayAdapter(//creamos este adaptador uno que existe
            this,//el contexto en el que usamos
            android.R.layout.simple_list_item_1,//como se va a ver
            arreglo //arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        //implementacion de que cuando se de clic, solo lo vamos a dejar escuchando
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)


        botonAnadirListView.setOnClickListener {  }
    }
}