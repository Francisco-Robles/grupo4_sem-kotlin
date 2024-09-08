package com.example.grupo4_sem_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvPeliculas: RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvPeliculas = findViewById(R.id.rvPeliculas)
        peliculasAdapter = PeliculaAdapter(getPeliculas(), this)
        rvPeliculas.adapter = peliculasAdapter

    }

    private fun getPeliculas(): MutableList<Pelicula>{

        val peliculas: MutableList<Pelicula> = ArrayList()

        peliculas.add(Pelicula(1,"Pelicula 1", 2020, "Genero 1", false))
        peliculas.add(Pelicula(2,"Pelicula 2", 2021, "Genero 2", false))
        peliculas.add(Pelicula(3,"Pelicula 3", 2022, "Genero 1", true))
        peliculas.add(Pelicula(4,"Pelicula 4", 2023, "Genero 2", false))

        return peliculas

    }

}