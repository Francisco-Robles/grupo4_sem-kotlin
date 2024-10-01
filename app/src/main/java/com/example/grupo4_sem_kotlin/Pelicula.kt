package com.example.grupo4_sem_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas_entity")
data class Pelicula (

    @ColumnInfo(name = "titulo") var titulo: String,
    @ColumnInfo(name = "anio_lanzamiento") var anio_lanzamiento: Int,
    @ColumnInfo(name = "genero") var genero: String,
    @ColumnInfo(name = "para_adultos") var para_adultos: Boolean

){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}