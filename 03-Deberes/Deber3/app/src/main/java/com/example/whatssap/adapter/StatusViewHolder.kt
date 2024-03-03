package com.example.whatssap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatssap.EstadosVar
import com.example.whatssap.R

class StatusViewHolder (val view: View): RecyclerView.ViewHolder(view) {
    fun render(estadosModel: EstadosVar){
    val nombreStatus = view.findViewById<TextView>(R.id.id_NombreContacto)
    val  dateStatus= view.findViewById<TextView>(R.id.hora_publicada)
    val fotoStatus = view.findViewById<ImageView>(R.id.imagen_contacto)

        nombreStatus.text = estadosModel.nombreEstado
        dateStatus.text = estadosModel.dateEstado
        Glide.with(fotoStatus.context).load(estadosModel.fotoEstado).into(fotoStatus)
    }
}