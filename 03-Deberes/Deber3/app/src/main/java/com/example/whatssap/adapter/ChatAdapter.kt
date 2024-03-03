package com.example.whatssap.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whatssap.PantallaPrincipal
import com.example.whatssap.R

class ChatAdapter (private val chatList: List<PantallaPrincipal>): RecyclerView.Adapter<ChatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return ChatViewHolder(layoutInflater.inflate(R.layout.item_mensajes,parent,false))

}

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = chatList[position]
        holder.render(item)

    }







}