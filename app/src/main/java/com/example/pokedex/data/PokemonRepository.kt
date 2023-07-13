package com.example.pokedex.data

import com.example.pokedex.data.database.PokemonDao
import com.example.pokedex.data.database.toPokemonEntity
import com.example.pokedex.data.network.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val pokemonDao: PokemonDao
) {

    suspend fun getNewPokemon(): Pokemon? {
        var numberRandom = (0..1000).random()
        val response = api.getPokemon(numberRandom)
        return (if (response != null) {
            pokemonDao.insert(response.toPokemonEntity(numberRandom))
            pokemonDao.getAll().map { it.toPokemon() }.last()
        } else {
            null
        }
                )
    }

    suspend fun getCurrentPokemonList(): List<Pokemon> =
        pokemonDao.getAll().map { it.toPokemon() }
}


