package com.example.pokedex.data

import com.example.pokedex.data.database.toPokemonEntity
import com.example.pokedex.data.network.PokemonService

class PokemonRepository {
    private val api = PokemonService()

    suspend fun getNewPokemon(): Pokemon? {
        var numberRandom = (0..1000).random()
        val response = api.getPokemon(numberRandom)

        return (if (response != null) {
            PokemonProvider.listOfPokemonSaved.add(response.toPokemonEntity(numberRandom))
            PokemonProvider.listOfPokemonSaved.map{ it.toPokemon() }.last()
        } else {
            null
        }
                )
    }

    suspend fun getCurrentPokemonList(): List<Pokemon> =
        PokemonProvider.listOfPokemonSaved.map { it.toPokemon() }
}


