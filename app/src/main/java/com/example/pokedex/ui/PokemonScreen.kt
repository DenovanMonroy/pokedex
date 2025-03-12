package com.example.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonScreen(viewModel: PokemonViewModel) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val pokemon by viewModel.pokemon.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar Pokémon") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.fetchPokemon(searchText.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            pokemon?.let { pokemonData ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = pokemonData.name.uppercase(), fontSize = 24.sp, color = Color.Blue)
                    Spacer(modifier = Modifier.height(8.dp))

                    // Imagen del Pokémon
                    Image(
                        painter = rememberImagePainter(pokemonData.sprites.front_default),
                        contentDescription = pokemonData.name,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Tabla de información
                    PokemonTable(pokemonData)
                }
            } ?: Text("No se encontró el Pokémon", color = Color.Red)
        }
    }
}

@Composable
fun PokemonTable(pokemon: com.example.pokedex.model.Pokemon) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TableRow(label = "ID", value = pokemon.id.toString())
        TableRow(label = "Peso", value = "${pokemon.weight} kg")
        TableRow(label = "Altura", value = "${pokemon.height} m")
        TableRow(label = "Experiencia Base", value = pokemon.base_experience.toString())
        TableRow(label = "Tipos", value = pokemon.types.joinToString { it.type.name })

        // Sección de estadísticas
        Text(text = "Estadísticas", fontSize = 18.sp, color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
        pokemon.stats.forEach { stat ->
            TableRow(label = stat.stat.name.replace("-", " ").capitalize(), value = stat.base_stat.toString())
        }
    }
}

@Composable
fun TableRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 16.sp, color = Color.DarkGray)
        Text(text = value, fontSize = 16.sp, color = Color.Black)
    }
}
