package com.example.deber03whatssap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03whatssap.R
import com.example.deber03whatssap.modelo.PantallaPrincipal

class MensajesAdapter (val mensajesList: List<PantallaPrincipal>):  RecyclerView.Adapter<MensajesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return MensajesViewHolder(layoutInflater.inflate(R.layout.item_mensajes,parent, false))
    }

    override fun onBindViewHolder(holder: MensajesViewHolder, position: Int) {
        val item = mensajesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return mensajesList.size
    }

}