package com.example.pokedex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentListOfPokemonBinding

class ListOfPokemon : Fragment() {

    private val binding : FragmentListOfPokemonBinding by lazy {
        FragmentListOfPokemonBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //cambiar el argumento
        val action =  ListOfPokemonDirections.actionListOfPokemonToDetailPokemon(DetailPokemonModel("Prueba1", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"))
        binding.btShowPokemon.setOnClickListener {
            findNavController().navigate(action)
        }
    }

}