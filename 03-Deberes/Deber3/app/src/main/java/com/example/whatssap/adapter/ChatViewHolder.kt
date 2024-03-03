package com.example.whatssap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.whatssap.PantallaPrincipal
import com.example.whatssap.R

class ChatViewHolder (val view: View):ViewHolder(view) {

    fun render(mensajeModel: PantallaPrincipal){

        val fecha = view.findViewById<TextView>(R.id.horadate)
        val nombre = view.findViewById<TextView>(R.id.id_NombreSMContacto)
        val mensaje = view.findViewById<TextView>(R.id.id_contenidoMensaje)
        val foto = view.findViewById<ImageView>(R.id.imagen_sendUser)
        Glide.with(foto.context).load(mensajeModel.photo).into(foto)
            fecha.text = mensajeModel.date
            nombre.text = mensajeModel.mensajeNombre
            mensaje.text = mensajeModel.mensajeText

        }
}