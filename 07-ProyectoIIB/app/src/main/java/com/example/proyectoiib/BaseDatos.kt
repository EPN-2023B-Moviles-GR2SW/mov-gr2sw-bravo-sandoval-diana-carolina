package com.example.proyectoiib

class BaseDatos {
    companion object{
        val arregloPeliculasPorVer = arrayListOf<Pelicula>()
        val arregloPeliculasVistas = arrayListOf<Pelicula>()

        init{
            arregloPeliculasPorVer.add(
                Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.mscpelicula)
            )
            arregloPeliculasPorVer.add(Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.mscpelicula))
            arregloPeliculasPorVer.add(Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.mscpelicula))

            arregloPeliculasPorVer.add(Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.mscpelicula))

            arregloPeliculasPorVer.add(Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.mscpelicula))


        }
    }
}