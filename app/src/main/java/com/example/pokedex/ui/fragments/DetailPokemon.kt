package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.FragmentDetailPokemonBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemon : Fragment() {

    private val binding: FragmentDetailPokemonBinding by lazy {
        FragmentDetailPokemonBinding.inflate(layoutInflater)
    }
    private val args: DetailPokemonArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPokemonName.text = args.detailPokemon.name
        val img: String = args.detailPokemon.Img
        Picasso.get().load(img).into(binding.ivPokemon)

        binding.btnOk.setOnClickListener {
            val action = DetailPokemonDirections.actionDetailPokemonToListOfPokemon()
            findNavController().navigate(action)
        }
    }

}