package com.example.grupo4_sem_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

class PeliculaListFragment : Fragment() {

    private lateinit var rvPeliculas: RecyclerView
    private lateinit var peliculasAdapter: PeliculaAdapter
    private lateinit var peliculaDao: PeliculaDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.primerfragmento, container, false)

        rvPeliculas = view.findViewById(R.id.rvPeliculas)
        peliculasAdapter = PeliculaAdapter(mutableListOf(), requireContext())
        rvPeliculas.adapter = peliculasAdapter
        rvPeliculas.layoutManager = LinearLayoutManager(requireContext())


        viewLifecycleOwner.lifecycleScope.launch {
            fetchAndSavePeliculas()
            loadPeliculasFromDatabase()
        }

        return view
    }

    private suspend fun fetchAndSavePeliculas() {
        val peliculas = getPeliculasFromApi()
        peliculas.forEach { pelicula ->
            val peliculaEntity = Pelicula(
                titulo = pelicula.titulo,
                anio_lanzamiento = pelicula.anio_lanzamiento,
                genero = pelicula.genero,
                para_adultos = pelicula.para_adultos,
                poster_path = pelicula.poster_path
            )
            peliculaDao.insert(peliculaEntity)
        }
    }

    private suspend fun getPeliculasFromApi(): List<Pelicula> {
        return withContext(Dispatchers.IO) {
            try {
                val token =
                    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTIwODA1NzNmOWRjY2I4MTVjMGI0YWU5NmUwN2QzZCIsIm5iZiI6MTcyNTcyODQxNi44NTMzMjUsInN1YiI6IjY2ZDY0MDM1NTcxOGUzZTNiZTA0MjU0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.bYZOvGcQXzb2Szdn18j9KEGacGbBd0mR5zSZEsZcN4g"
                val response = RetrofitInstance.api.getPopularMovies(token)
                if (response.isSuccessful) {
                    response.body()?.results ?: emptyList()
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                println("Error fetching movies: ${e.message}")
                emptyList()
            }
        }
    }

    private suspend fun loadPeliculasFromDatabase() {

        val peliculas = peliculaDao.getAllPeliculas()
        peliculasAdapter.updatePeliculas(peliculas)
    }

    object RetrofitInstance {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        interface ApiService {
            @GET("/3/movie/popular")
            suspend fun getPopularMovies(@Header("Authorization") token: String): Response<MovieResponse>
        }
        val api: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }

        data class MovieResponse(
            val results: List<Pelicula>
        )

    }
}

