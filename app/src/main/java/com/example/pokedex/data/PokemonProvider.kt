package com.example.pokedex.data

import com.example.pokedex.data.database.PokemonEntity

class PokemonProvider {
    companion object{
        var listOfPokemonSaved = mutableListOf<PokemonEntity>(
            PokemonEntity(1,"ejemplo1","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/11.png"),
            PokemonEntity(2,"ejemplo2","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/12.png"),
            PokemonEntity(3,"ejemplo3","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/13.png"),
        )

    }
}


