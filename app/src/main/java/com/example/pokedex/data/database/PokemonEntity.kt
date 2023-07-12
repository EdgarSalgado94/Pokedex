package com.example.pokedex.data.database

import com.example.pokedex.data.network.PokemonResponse

data class PokemonEntity(
    val numberOnServer: Int,
    val name: String,
    val img: String
)

fun PokemonResponse.toPokemonEntity(numberOnServer: Int): PokemonEntity = PokemonEntity(numberOnServer,name,sprites.other.officialArtwork.img)
