package com.example.pokedex.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex.data.database.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE numberOnServer = :num")
    fun getByNumberOnServer(num: Int): PokemonEntity

    @Insert
    fun insert(pokemonEntity: PokemonEntity)


}