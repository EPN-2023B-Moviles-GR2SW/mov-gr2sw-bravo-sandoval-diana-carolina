package com.example.proyectoiib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        inicializarRecyclerViewPeliculasVer()
        //inicializarRecycleViewPeliculasVistas()
    }
        fun inicializarRecyclerViewPeliculasVer(){
            val recyclerView = findViewById<RecyclerView>(R.id.rv_peliculasPorVer)

            // Configura el LinearLayoutManager con orientación horizontal
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            val adaptador = AdaptadorPeliculasPorVer(
                this,
                BaseDatos.arregloPeliculasPorVer,
                recyclerView
            )

            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adaptador
            recyclerView.itemAnimator = DefaultItemAnimator()
            adaptador.notifyDataSetChanged()
        }

        /*fun inicializarRecyclerViewJuegosNuevos(){
            val recyclerView = findViewById<RecyclerView>(R.id.rv_juegos_nuevos)

            // Configura el LinearLayoutManager con orientación horizontal
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            val adaptador = RVAdaptJuegosPopulares(
                this,
                BaseDeDatos.arregloJuegosNuevos,
                recyclerView
            )

            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adaptador
            recyclerView.itemAnimator = DefaultItemAnimator()
            adaptador.notifyDataSetChanged()


    }*/
}