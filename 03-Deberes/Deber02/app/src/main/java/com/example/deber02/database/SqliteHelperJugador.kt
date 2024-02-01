package com.example.deber02.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelperJugador(contexto: Context,
    ):SQLiteOpenHelper(
        contexto,
        "jugadores", null,
        1
    ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaJugador =
            """
                CREATE TABLE JUGADOR(
                ID_JUGADOR INTEGER PRIMARY KEY,
                NOMBRE VARCHAR(100),
                EDAD INTEGER,
                ESTATURA DOUBLE,
                NUMERO INTEGER,
                IDEQUIPO INTEGER,
                 FOREIGN KEY(IDEQUIPO) REFERENCES EQUIPO(ID_EQUIPO)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaJugador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}