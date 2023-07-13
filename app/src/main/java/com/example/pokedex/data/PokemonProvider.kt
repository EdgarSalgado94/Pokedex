package com.example.pokedex.data

import com.example.pokedex.data.database.PokemonEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonProvider @Inject constructor() {
    var listOfPokemonSaved = mutableListOf<PokemonEntity>()
}


