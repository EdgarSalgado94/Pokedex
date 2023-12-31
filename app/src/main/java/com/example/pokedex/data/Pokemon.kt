package com.example.pokedex.data

import com.example.pokedex.data.database.PokemonEntity

data class Pokemon(
    val name: String,
    val img: String
)

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(name,img)