package com.example.pokedex.data

class PokemonProvider {
    companion object{
        var listOfPokemonSaved = mutableListOf<Pokemon>(
            Pokemon(1,"ejemplo1","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/11.png"),
            Pokemon(2,"ejemplo2","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/12.png"),
            Pokemon(3,"ejemplo3","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/13.png"),
        )

    }
}