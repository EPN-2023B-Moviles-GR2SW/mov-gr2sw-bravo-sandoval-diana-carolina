package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    var textoGlobal = ""
    fun mostrarSnackbar(texto: String){
        textoGlobal += texto
        val snack = Snackbar.make(findViewById(R.id.cl_ciclo_vida),
        textoGlobal, Snackbar.LENGTH_INDEFINITE) //se puede cambiar el tiempo que se demora
        snack.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackbar("HOLA")
        mostrarSnackbar("OnCreate")
    }
    override fun onStart(){
        super.onStart()
        mostrarSnackbar("onStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle,) {

        outState.run {
            //GUARDAR LAS VARIABLES
           //PRIMITIVAS
           putString("textoGuardado",textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //RECUPERAR LAS VARIABLES
        //PRIMITIVO
        val textoRecuperado: String? = savedInstanceState.getString("textoGuardado")
        //val textoRecupaerado: Int? = sawdInstanceState
        //.getInt("numeroGuardado)"
        if(textoRecuperado!=null){

            mostrarSnackbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }
}

