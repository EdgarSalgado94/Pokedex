package com.example.pokedex.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokedex.data.network.PokemonResponse
@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val numberOnServer: Int,
    val name: String,
    val img: String
)

fun PokemonResponse.toPokemonEntity(numberOnServer: Int): PokemonEntity = PokemonEntity(
    numberOnServer = numberOnServer,
    name = name,
    img = sprites.other.officialArtwork.img
)
