package com.example.deber03whatssap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03whatssap.adapter.StatusAdapter

class Estados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estados)
        statusRecyclerView()
        val btnIrEstados = findViewById<Button>(R.id.id_irChats)
        btnIrEstados.setOnClickListener {
            val lanzar = Intent(this, MainActivity::class.java)
            startActivity(
                lanzar
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuwhatssap, menu)
        return true
    }

    private fun statusRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewEstados)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StatusAdapter(EstadosProvider.estadosList)
    }
}