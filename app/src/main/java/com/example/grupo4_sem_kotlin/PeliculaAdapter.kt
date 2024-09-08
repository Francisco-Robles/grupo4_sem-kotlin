package com.example.grupo4_sem_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeliculaAdapter (var peliculas: MutableList<Pelicula>, var context: Context):
    RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

    class  PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view){

        lateinit var txtTitulo: TextView
        lateinit var txtAnio: TextView
        lateinit var txtGenero: TextView
        lateinit var txtParaAdultos: TextView

        init {
            txtTitulo = view.findViewById(R.id.tvTitulo)
            txtAnio = view.findViewById(R.id.tvAnioLanzamiento)
            txtGenero = view.findViewById(R.id.tvGenero)
            txtParaAdultos = view.findViewById(R.id.tvParaAdultos)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeliculaAdapter.PeliculaViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)

    }

    override fun onBindViewHolder(holder: PeliculaAdapter.PeliculaViewHolder, position: Int) {

        val item = peliculas.get(position)
        holder.txtTitulo.text = item.titulo
        holder.txtAnio.text = item.anio_lanzamiento.toString()
        holder.txtGenero.text = item.genero
        holder.txtParaAdultos.text = item.para_adultos.toString()

    }

    override fun getItemCount() = peliculas.size

}