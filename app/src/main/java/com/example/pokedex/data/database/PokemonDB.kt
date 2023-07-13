package com.example.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDB: RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao
}