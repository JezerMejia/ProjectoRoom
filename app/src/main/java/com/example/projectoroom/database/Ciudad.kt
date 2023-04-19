package com.example.projectoroom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tblCiudad")
data class Ciudad(
    @PrimaryKey(true)
    val id: Int,
    @ColumnInfo("nombre")
    var nombre: String,
    @ColumnInfo("poblacion")
    var poblacion: Int,
)
