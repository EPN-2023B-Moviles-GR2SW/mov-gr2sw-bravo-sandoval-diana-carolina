package com.example.proyectoiib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPeliculasPorVer (
    private val contexto: Context, // Corregido: Cambiado a Context
    private val lista: ArrayList<Pelicula>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdaptadorPeliculasPorVer.MyViewHolder>() {

    // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgReciente: ImageView = itemView.findViewById(R.id.img_PeliculaPorVer)
    }

    // Setear el layour que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorPeliculasPorVer.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_peliculasporver, parent, false)
        return MyViewHolder(itemView)
    }

    // Función para devolver el núnero de elementos que hay dentro del arreglo
    override fun getItemCount(): Int {
        return lista.size
    }

    // Setear los datos para la iteracion
    override fun onBindViewHolder(holder: AdaptadorPeliculasPorVer.MyViewHolder, position: Int) {
        val recienteActual = lista[position]
        val resourceId = recienteActual.portadaPelicula
        holder.imgReciente.setImageResource(resourceId)
    }
}