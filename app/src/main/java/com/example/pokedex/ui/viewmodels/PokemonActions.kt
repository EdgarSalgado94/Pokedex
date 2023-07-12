package com.example.pokedex.ui.viewmodels

import com.example.pokedex.data.Pokemon

sealed class PokemonActions{
   data class ShowNewPokemon(val pokemon: Pokemon): PokemonActions()
   data class SetCurrentList(val list: List<Pokemon>): PokemonActions()
   data class Message(val msg: String): PokemonActions()
   data class Loading(val loading: Boolean): PokemonActions()
}
