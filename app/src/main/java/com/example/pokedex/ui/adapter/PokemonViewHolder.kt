package com.example.pokedex.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.Pokemon
import com.example.pokedex.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(private val view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemPokemonBinding.bind(view)
    fun render(pokemon: Pokemon,onClick: (Pokemon)-> Unit){

        with(binding){
            tvItemName.text = pokemon.name

            val img:String = pokemon.img
            Picasso.get().load(img).into(ivItem)

            cvItem.setOnClickListener {
                onClick(pokemon)
            }
        }

    }

}