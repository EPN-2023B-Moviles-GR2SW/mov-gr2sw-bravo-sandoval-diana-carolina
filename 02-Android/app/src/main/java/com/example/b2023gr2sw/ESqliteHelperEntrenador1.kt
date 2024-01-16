package com.example.b2023gr2sw

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador1 (
    contexto: Context?

): SQLiteOpenHelper(
    contexto,"moviles", // nombre bdd
    null, 1
    ) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}