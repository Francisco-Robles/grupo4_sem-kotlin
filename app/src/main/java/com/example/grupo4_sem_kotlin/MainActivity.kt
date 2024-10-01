package com.example.grupo4_sem_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvPeliculas: RecyclerView
    lateinit var peliculasAdapter: PeliculaAdapter
    lateinit var toolbar: Toolbar

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
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_listado){
            val intent = Intent(this, ListadoPeliculasFavsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getPeliculas(): MutableList<Pelicula>{

        val peliculas: MutableList<Pelicula> = ArrayList()
        /*var bdd = AppDatabase.getDatabase(applicationContext)
        peliculas.addAll(bdd.peliculaDao().getAll())*/
        peliculas.add(Pelicula("Pelicula 1", 2020, "Genero 1", false))
        peliculas.add(Pelicula("Pelicula 2", 2021, "Genero 2", false))
        peliculas.add(Pelicula("Pelicula 3", 2022, "Genero 1", true))
        peliculas.add(Pelicula("Pelicula 4", 2023, "Genero 2", false))

        return peliculas

    }

}