package com.example.pokedex.data.network

import com.example.pokedex.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemon(id: Int): PokemonResponse?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PokemonApiService::class.java).getPokemonById(id)
            response.body()
        }
    }

}