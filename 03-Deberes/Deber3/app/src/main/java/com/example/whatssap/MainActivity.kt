package com.example.whatssap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatssap.adapter.ChatAdapter
import com.example.whatssap.adapter.EstadoAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initChatRecycleView()

        val botonEstados = findViewById<Button>(R.id.id_irEstados)
        botonEstados.setOnClickListener {
            initEstadoRecycleView()
        }

        val botonChat = findViewById<Button>(R.id.ir_Mensajes)
        botonChat.setOnClickListener {
            initChatRecycleView()
        }



    }

    private fun initChatRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ChatAdapter(chatProvider.mensajesList)
    }

    private fun initEstadoRecycleView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= EstadoAdapter(EstadosProvider.estadosList)
    }
}