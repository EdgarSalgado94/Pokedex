package com.example.pokedex.data.network

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: Sprites
    )

data class Sprites(
    @SerializedName("other") val other: Other,
)

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
)

data class OfficialArtwork(
    @SerializedName("front_default") val img: String,
)