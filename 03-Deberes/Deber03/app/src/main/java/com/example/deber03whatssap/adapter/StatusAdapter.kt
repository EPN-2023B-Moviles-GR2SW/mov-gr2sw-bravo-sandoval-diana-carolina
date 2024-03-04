package com.example.deber03whatssap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03whatssap.R
import com.example.deber03whatssap.modelo.EstadosVar

class StatusAdapter (val estadosList: List<EstadosVar>):  RecyclerView.Adapter<StatusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return StatusViewHolder(layoutInflater.inflate(R.layout.items_estados,parent, false))
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val item = estadosList[position]
        holder.renderEstados(item)
    }

    override fun getItemCount(): Int {
        return estadosList.size
    }
}