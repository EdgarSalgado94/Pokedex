package com.example.pokedex.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val api:PokemonApiService
    ) {

    suspend fun getPokemon(id: Int): PokemonResponse?{
        return withContext(Dispatchers.IO){
            val response = api.getPokemonById(id)
            response.body()
        }
    }
}