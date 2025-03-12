package com.example.pokedex.model

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val base_experience: Int,
    val sprites: Sprites,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>
)

data class Sprites(
    val front_default: String
)

data class PokemonType(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class PokemonStat(
    val base_stat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String
)
