package com.example.grupo4_sem_kotlin

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PeliculaDao {

    @Query("SELECT * FROM peliculas_entity")
    fun getAll(): List<Pelicula>

}