package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.Pokemon
import com.example.pokedex.databinding.FragmentListOfPokemonBinding
import com.example.pokedex.ui.adapter.PokemonAdapter
import com.example.pokedex.ui.models.toDetailPokemonModel
import com.example.pokedex.ui.viewmodels.ListOfPokemonViewModel
import com.example.pokedex.ui.viewmodels.PokemonActions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfPokemon : Fragment() {

    private val binding : FragmentListOfPokemonBinding by lazy {
        FragmentListOfPokemonBinding.inflate(layoutInflater)
    }
    private val viewModel: ListOfPokemonViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter
    private var listAux = mutableListOf<Pokemon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindActionLiveData()
        initRecyclerView()

        binding.btShowPokemon.setOnClickListener {
            viewModel.getNewPokemon()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListOfPokemon()
    }

    private fun bindActionLiveData() {
        viewModel.getActionsLiveData().observe(viewLifecycleOwner,this::handleAction)
    }

    private fun handleAction(actions: PokemonActions){
        when (actions) {
            is PokemonActions.Message -> showMessage(actions.msg)
            is PokemonActions.ShowNewPokemon -> showNewPokemon(actions.pokemon)
            is PokemonActions.Loading -> loadingBar(actions.loading)
            is PokemonActions.SetCurrentList -> setCurrentList(actions.list)
        }
    }

    private fun setCurrentList(list: List<Pokemon>) {
        listAux.clear()
        listAux.addAll(list)
        adapter.notifyDataSetChanged()
    }

    private fun loadingBar(loading: Boolean) {
        if (loading) {
            binding.pBar.isVisible
        }else
            binding.pBar.isGone
    }

    private fun initRecyclerView() {
        adapter = PokemonAdapter(listAux,this::navigateToDetailPokemon)
        binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPokemon.adapter = adapter
    }

    private fun showMessage(msg: String) {
        loadingBar(false)
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailPokemon(pokemon: Pokemon){
        val model = pokemon.toDetailPokemonModel()
        val action = ListOfPokemonDirections.actionListOfPokemonToDetailPokemon(model)
        findNavController().navigate(action)
    }

    private fun showNewPokemon(pokemon: Pokemon) {
        loadingBar(false)
        navigateToDetailPokemon(pokemon)
    }
}