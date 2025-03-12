package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedex.ui.PokemonScreen
import com.example.pokedex.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemonViewModel: PokemonViewModel = viewModel()
            PokemonScreen(viewModel = pokemonViewModel)
        }
    }
}
