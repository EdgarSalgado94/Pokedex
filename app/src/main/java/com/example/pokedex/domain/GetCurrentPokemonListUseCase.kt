package com.example.pokedex.domain

import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonRepository
import javax.inject.Inject

class GetCurrentPokemonListUseCase @Inject constructor(
    private val repository:PokemonRepository) {
    suspend operator fun invoke(): List<Pokemon> = repository.getCurrentPokemonList()
}