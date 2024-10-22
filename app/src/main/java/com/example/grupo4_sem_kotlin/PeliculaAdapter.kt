package com.example.grupo4_sem_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PeliculaAdapter(private var peliculas: MutableList<Pelicula>, private var context: Context) :
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {

    class PeliculaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val txtAnio: TextView = view.findViewById(R.id.tvAnioLanzamiento)
        val txtGenero: TextView = view.findViewById(R.id.tvGenero)
        val txtParaAdultos: TextView = view.findViewById(R.id.tvParaAdultos)
        val ivPoster: ImageView = view.findViewById(R.id.ivPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculas[position]
        holder.txtTitulo.text = item.titulo
        holder.txtAnio.text = item.anio_lanzamiento.toString()
        holder.txtGenero.text = item.genero
        holder.txtParaAdultos.text = item.para_adultos.toString()


        val imageUrl = "https://image.tmdb.org/t/p/w500${item.poster_path}"
        Glide.with(context)
            .load(imageUrl)
            .into(holder.ivPoster)
    }

    override fun getItemCount() = peliculas.size

    fun updatePeliculas(nuevasPeliculas: List<Pelicula>) {
        peliculas.clear()
        peliculas.addAll(nuevasPeliculas)
        notifyDataSetChanged()
    }
}



