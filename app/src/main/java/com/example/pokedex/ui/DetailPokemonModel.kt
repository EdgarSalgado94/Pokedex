package com.example.pokedex.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailPokemonModel(
    val name: String,
    val Img: String
    ) : Parcelable
