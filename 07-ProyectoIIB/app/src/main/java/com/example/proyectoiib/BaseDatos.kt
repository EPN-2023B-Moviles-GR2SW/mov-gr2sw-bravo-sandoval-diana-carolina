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
                "Barbie","1h:50:00","Romance",2015,R.drawable.barbie))
            arregloPeliculasPorVer.add(Pelicula(
                "Insidious","1h:50:00","Terror",2015,R.drawable.nochedemonio))

            arregloPeliculasPorVer.add(Pelicula(
                "Jack","1h:50:00","Terror",2015,R.drawable.jack))

            arregloPeliculasPorVer.add(Pelicula(
                "SociedadNieve","1h:50:00","Suspenso",2015,R.drawable.sociedad))


        }
        init{
            arregloPeliculasVistas.add(
                Pelicula(
                    "Antes de ti","1h:50:00","Romance",2015,R.drawable.antesdeti)
            )
            arregloPeliculasVistas.add(Pelicula(
                "Estacion Zombie","1h:50:00","Terror",2015,R.drawable.estacionzombie))
            arregloPeliculasVistas.add(Pelicula(
                "Avengers","1h:50:00","Accion",2015,R.drawable.avengers))

            arregloPeliculasVistas.add(Pelicula(
                "El Conjuto","1h:50:00","Terror",2015,R.drawable.conjuro))

            arregloPeliculasVistas.add(Pelicula(
                "Tres metros sobre el cielo","1h:50:00","Romance",2015,R.drawable.shrek))
        }
    }
}