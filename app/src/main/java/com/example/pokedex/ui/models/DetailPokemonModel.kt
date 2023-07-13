package com.example.pokedex.ui.models

import android.os.Parcelable
import com.example.pokedex.data.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailPokemonModel(
    val name: String,
    val Img: String
    ) : Parcelable
fun Pokemon.toDetailPokemonModel(): DetailPokemonModel = DetailPokemonModel(name,img)