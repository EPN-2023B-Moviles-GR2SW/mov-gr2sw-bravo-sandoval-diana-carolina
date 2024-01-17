package com.example.b2023gr2sw

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class GGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
    }
    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager.findFragmentById(
            R.id.map
        ) as SupportMapFragment
        fragmentoMapa.getMapAsync{
            googleMap -> //with(x) => if(X!=null) with
            (googleMap){
                mapa=googleMap
                estableceConfiguracionMapa()
            }
        }
    }

    fun estableceConfiguracionMapa(){
        val contexto = this.applicationContext with(mapa){
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        }
    }
    fun solicitarPermisos(){
            val contexto = this.applicationContext
        val nombrePermiso = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisosFineLocation = ContextCompat.checkSelfPermission(contexto,
            //nombre del permiso que va a chekear
        nombrePermiso)
        val tienePermisos = permisosFineLocation== PackageManager.PERMISSION_GRANTED
        if (tienePermisos){
            permisos=true
        }else {
            ActivityCompat.requestPermissions(
                this,//Contexto
                //arelgo de los permisos
                arrayOf(
                    nombrePermiso,nombrePermisoCoarse
                ),1// codigo de peticion de los permisos
            )
        }
    }
}