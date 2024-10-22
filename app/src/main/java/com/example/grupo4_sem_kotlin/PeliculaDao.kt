package com.example.grupo4_sem_kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PeliculaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pelicula: Pelicula)

    @Query("SELECT * FROM peliculas_entity")
    suspend fun getAllPeliculas(): List<Pelicula>
}