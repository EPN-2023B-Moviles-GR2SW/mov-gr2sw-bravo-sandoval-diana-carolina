package com.example.deber03whatssap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03whatssap.R
import com.bumptech.glide.Glide
import com.example.deber03whatssap.modelo.PantallaPrincipal

class MensajesViewHolder (view: View): RecyclerView.ViewHolder(view){
    val fecha = view.findViewById<TextView>(R.id.horadate)
    val nombre = view.findViewById<TextView>(R.id.idMensajeName)
    val mensaje = view.findViewById<TextView>(R.id.idMensaje)
    val foto = view.findViewById<ImageView>(R.id.idFotoMensajes)
    fun render(mensajeModel: PantallaPrincipal){
        fecha.text = mensajeModel.date
        nombre.text = mensajeModel.mensajeNombre
        mensaje.text = mensajeModel.mensajeText
        Glide.with(foto.context).load(mensajeModel.photo).into(foto)
    }
}