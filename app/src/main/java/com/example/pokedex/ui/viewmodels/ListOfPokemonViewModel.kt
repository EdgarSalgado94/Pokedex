package com.example.pokedex.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.GetCurrentPokemonListUseCase
import com.example.pokedex.domain.GetNewPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfPokemonViewModel @Inject constructor(
    private val getNewPokemonUseCase: GetNewPokemonUseCase,
    private val getCurrentPokemonListUseCase: GetCurrentPokemonListUseCase
) : ViewModel() {

    private val action = MutableLiveData<PokemonActions>()
    fun getActionsLiveData() = action as LiveData<PokemonActions>

    fun getNewPokemon() {
        action.postValue(PokemonActions.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            val newPokemon = getNewPokemonUseCase()
            if (newPokemon != null) {
                action.postValue(PokemonActions.ShowNewPokemon(newPokemon))
            } else {
                action.postValue(PokemonActions.Message("Ha ocurrido un error!"))
            }
        }
    }

    fun getListOfPokemon() {
        action.postValue(PokemonActions.Loading(true))
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonCurrentList = getCurrentPokemonListUseCase()
            if (!pokemonCurrentList.isNullOrEmpty()) {
                action.postValue(PokemonActions.SetCurrentList(pokemonCurrentList))
            }
        }
    }
}