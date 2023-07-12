package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonProvider
import com.example.pokedex.databinding.FragmentListOfPokemonBinding
import com.example.pokedex.ui.adapter.PokemonAdapter
import com.example.pokedex.ui.models.DetailPokemonModel
import com.example.pokedex.ui.models.toDetailPokemonModel
import com.example.pokedex.ui.viewmodels.ListOfPokemonViemModel

class ListOfPokemon : Fragment() {

    private val binding : FragmentListOfPokemonBinding by lazy {
        FragmentListOfPokemonBinding.inflate(layoutInflater)
    }

    private val viewModel: ListOfPokemonViemModel by viewModels()

    private val listAux = PokemonProvider.listOfPokemonSaved

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        binding.btShowPokemon.setOnClickListener {
           // ir por un nuevo pokemon y luego navigateToDetailPokemon(...)

        }
    }
    private fun initRecyclerView() {
        val recyclerView = binding.rvPokemon
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PokemonAdapter(listAux,this::navigateToDetailPokemon)
    }
    private fun navigateToDetailPokemon(pokemon: Pokemon){
        val model = pokemon.toDetailPokemonModel()
        val action = ListOfPokemonDirections.actionListOfPokemonToDetailPokemon(model)
        findNavController().navigate(action)
    }

}