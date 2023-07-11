package com.example.pokedex.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper() {
    companion object {
        const val BASE = "https://pokeapi.co/api/v2/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}

