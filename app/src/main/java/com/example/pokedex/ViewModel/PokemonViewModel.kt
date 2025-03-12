
package com.example.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.Pokemon
import com.example.pokedex.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = RetrofitInstance.api.getPokemon(name.lowercase())
                _pokemon.value = result
            } catch (e: Exception) {
                _pokemon.value = null
            }
            _isLoading.value = false
        }
    }
}
