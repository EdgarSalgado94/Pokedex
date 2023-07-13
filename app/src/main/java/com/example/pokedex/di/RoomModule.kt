package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.data.database.PokemonDB
import com.example.pokedex.data.network.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATA_BASE_NAME = "PokemonDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,PokemonDB::class.java, DATA_BASE_NAME).build()

    @Singleton
    @Provides
    fun providePokemonDao(db : PokemonDB) = db.pokemonDao()

}