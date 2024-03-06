package com.example.proyectoiib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPeliculasVistas(private val contexto: Context, // Corregido: Cambiado a Context
                               private val lista: ArrayList<Pelicula>,
                               private val recyclerView: RecyclerView
)
    : RecyclerView.Adapter<AdaptadorPeliculasVistas.MyViewHolder>(){

        // Inicializar los componentes visuales de la Interfaz para el Adaptador Personalizado
        inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
            val imgReciente: ImageView = itemView.findViewById(R.id.img_PeliculaVista)
        }

        // Setear el layour que vamos a utilizar
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorPeliculasVistas.MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_peliculasvistas, parent, false)
            return MyViewHolder(itemView)
        }

        // Función para devolver el núnero de elementos que hay dentro del arreglo
        override fun getItemCount(): Int {
            return lista.size
        }

        // Setear los datos para la iteracion
        override fun onBindViewHolder(holder: AdaptadorPeliculasVistas.MyViewHolder, position: Int) {
            val recienteActual = lista[position]
            val resourceId = recienteActual.portadaPelicula
            holder.imgReciente.setImageResource(resourceId)
        }
}