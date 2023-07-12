package com.example.pokedex.domain

import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonRepository

class GetNewPokemonUseCase() {
    private val repository = PokemonRepository()
    suspend operator fun invoke(): Pokemon? = repository.getNewPokemon()
}