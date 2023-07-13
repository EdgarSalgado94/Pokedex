package com.example.pokedex.data

import com.example.pokedex.data.database.toPokemonEntity
import com.example.pokedex.data.network.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api:PokemonService,
    private val pokemonProvider:PokemonProvider
    ){
    suspend fun getNewPokemon(): Pokemon? {
        var numberRandom = (0..1000).random()
        val response = api.getPokemon(numberRandom)
        return (if (response != null) {
            pokemonProvider.listOfPokemonSaved.add(response.toPokemonEntity(numberRandom))
            pokemonProvider.listOfPokemonSaved.map{ it.toPokemon() }.last()
        } else {
            null
        }
                )
    }

    suspend fun getCurrentPokemonList(): List<Pokemon> =
        pokemonProvider.listOfPokemonSaved.map { it.toPokemon() }
}


