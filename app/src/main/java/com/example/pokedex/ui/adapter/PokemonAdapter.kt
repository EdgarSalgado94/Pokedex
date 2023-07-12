package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Pokemon

class PokemonAdapter(private var pokemonList: List<Pokemon>, private val onClick: (Pokemon)-> Unit): RecyclerView.Adapter<PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon,parent,false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.render(pokemonList[position],onClick)

    }

    override fun getItemCount() = pokemonList.size

}